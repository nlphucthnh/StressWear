package com.web.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.VaiTroDAO;
import com.web.DAO.VaiTroTaiKhoanDAO;
import com.web.Entity.TaiKhoan;
import com.web.service.AsyncEmailSender;
import com.web.service.MailerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/taikhoan")
public class TaiKhoanRestController {

    @Autowired
    TaiKhoanDAO taiKhoanDAO;

    @Autowired
    VaiTroDAO vaiTroDAO;

    @Autowired
    HttpSession session;

    @Autowired
    VaiTroTaiKhoanDAO vaiTroTaiKhoanDAO;

    @Autowired
    MailerService mailer;

    @GetMapping("/rest/authorities")
    public Map<String, Object> getAuthorities() {
        Map<String, Object> data = new HashMap<>();
        data.put("authorities", vaiTroTaiKhoanDAO.findAll());
        data.put("accounts", taiKhoanDAO.findAll());
        data.put("roles", vaiTroDAO.findAll());
        return data;
    }

    @GetMapping
    public ResponseEntity<List<TaiKhoan>> findAll() {
        return ResponseEntity.ok(taiKhoanDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TaiKhoan> findById(@PathVariable("id") String idTaiKhoan) {
        Optional<TaiKhoan> optional = taiKhoanDAO.findById(idTaiKhoan);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());

    }

    @GetMapping("numberConfirm")
    public ResponseEntity<String> getNumberConfirm() {
        String numberConfirm =  (String) session.getAttribute("numberConfirm");
        return ResponseEntity.ok(numberConfirm);
    }

    @GetMapping("taikhoandangky")
    public ResponseEntity<TaiKhoan> getTaiKhoanDangKy() {
        TaiKhoan TaiKhoanDangKy =  (TaiKhoan) session.getAttribute("TaiKhoanDangKy");
        return ResponseEntity.ok(TaiKhoanDangKy);
    }


    @GetMapping("paging")
    public ResponseEntity<Page<TaiKhoan>> findAllPage2(
            @RequestParam(name = "name", defaultValue = "") String nameaccount,
            @RequestParam("page") Optional<Integer> numberpage) {
        Pageable pageableTK = PageRequest.of(numberpage.orElse(0), 5);
        Page<TaiKhoan> pageTaiKhoan = taiKhoanDAO.findByTenDangNhap(nameaccount, pageableTK);
        return ResponseEntity.ok(pageTaiKhoan);
    }

    @PostMapping("email")
    public ResponseEntity<TaiKhoan> postEmail(@RequestBody TaiKhoan taiKhoan) throws MessagingException {
        if (!taiKhoanDAO.existsById(taiKhoan.getTenDangNhap())) {
            String numberConfirm = generateRandomNumbersString(6);
            session.setAttribute("numberConfirm", numberConfirm);
            session.setAttribute("TaiKhoanDangKy", taiKhoan);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            String subject = "Mã xác nhận đăng ký tài khoản - Ztreet Zear Shop";
            String body = bodyEmail(formattedDateTime, numberConfirm);
            AsyncEmailSender emailSender = new AsyncEmailSender(mailer);
            emailSender.sendEmail(taiKhoan.getEmail(), subject, body);
        }
        return ResponseEntity.ok(taiKhoan);
    }

    @PostMapping()
    public ResponseEntity<TaiKhoan> post(@RequestBody TaiKhoan taiKhoan) {
        if (taiKhoanDAO.existsById(taiKhoan.getTenDangNhap())) {
            return ResponseEntity.badRequest().build();
            // 400 Bad Request: Địa chỉ tồi
        }
        taiKhoanDAO.save(taiKhoan);
        return ResponseEntity.ok(taiKhoan);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaiKhoan> put(@PathVariable("id") String tenDangNhap, @RequestBody TaiKhoan taiKhoan) {
        if (!taiKhoanDAO.existsById(tenDangNhap)) {
            return ResponseEntity.notFound().build();
        }
        taiKhoanDAO.save(taiKhoan);
        return ResponseEntity.ok(taiKhoan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String tenDangNhap) {
        if (!taiKhoanDAO.existsById(tenDangNhap)) {
            return ResponseEntity.notFound().build();
        }
        taiKhoanDAO.deleteById(tenDangNhap);
        return ResponseEntity.ok().build();
    }
// -----------------
    public static String generateRandomNumbersString(int n) {
        String numbers = "";
        for (int i = 0; i < n; i++) {
            numbers += (int) (Math.random() * 9);
        }
        return numbers;
    }

    String bodyEmail(String formattedDateTime, String numberConfirm) {
        String body = "<html>" +
                "<head>" +
                "<title>Xác nhận đăng ký tài khoản</title>" +
                "<style>" +
                "body {" +
                "font-family: Arial, sans-serif;" +
                "margin: 0;" +
                "padding: 0;" +
                "background-color: #f4f4f4;" +
                "color: #333333;" +
                "}" +
                ".email-container {" +
                "max-width: 600px;" +
                "margin: 0 auto;" +
                "background-color: #ffffff;" +
                "padding: 20px;" +
                "border-radius: 8px;" +
                "box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);" +
                "}" +
                ".header {" +
                "background-color: #151655;" +
                "color: #ffffff;" +
                "padding: 15px;" +
                "text-align: center;" +
                "border-top-left-radius: 8px;" +
                "border-top-right-radius: 8px;" +
                "}" +
                ".footer {" +
                "background-color: #f4f4f4;" +
                "padding: 10px;" +
                "text-align: center;" +
                "border-bottom-left-radius: 8px;" +
                "border-bottom-right-radius: 8px;" +
                "}" +
                "h1 {" +
                "color: #ffffff;" +
                "margin-bottom: 20px;" +
                "}" +
                "p {" +
                "color: #666666;" +
                "margin-bottom: 10px;" +
                "}" +
                ".verification-code {" +
                "font-size: 24px;" +
                "color: #111b53;" +
                "font-weight: bold;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"email-container\">" +
                "<div class=\"header\">" +
                "<h1>Xác nhận đăng ký tài khoản</h1>" +
                "</div>" +
                "<div class=\"content\">" +
                "<h2>Chào bạn,</h2>" +
                "<p>Cảm ơn bạn đã ủng hộ Ztreet Zear Shop. Dưới đây là mã OTP của bạn:</p>" +
                "<p>Mã này được tạo vào lúc: <b>" + formattedDateTime + "</b></p>" +
                "<p class=\"verification-code\">Mã Verification của bạn: <b>" + numberConfirm + "</b></p>" +
                "<p>Mã này được tạo tự động, vui lòng không trả lời.</p>" +
                "</div>" +
                "<div class=\"footer\">" +
                "<p>Xin cảm ơn và chúc bạn có trải nghiệm tốt tại Ztreet Zear Shop.</p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
        return body;
    }
}
