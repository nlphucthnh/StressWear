// package com.web.RestController;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.web.DAO.SanPhamChiTietDAO;
// import com.web.Entity.SanPhamChiTiet;

// @RestController
// @CrossOrigin(origins = "*")
// @RequestMapping("/api/sanphamchitiet")
// public class SanPhamChiTietRestController {

//     @Autowired
//     SanPhamChiTietDAO sanPhamChiTietDAO;

//     @GetMapping
//     public ResponseEntity<List<SanPhamChiTiet>> findAll(){
//         return ResponseEntity.ok(sanPhamChiTietDAO.findAll());
//     }

//     @GetMapping("{id}")
//     public ResponseEntity<SanPhamChiTiet> findById(@PathVariable("id") String idSanPhamChiTiet){
//        Optional<SanPhamChiTiet> optional = sanPhamChiTietDAO.findById(Integer.valueOf(idSanPhamChiTiet));
//         if(!optional.isPresent()){
//             return ResponseEntity.notFound().build();
//         }
//         return ResponseEntity.ok(optional.get());
        
//     }

//     @PostMapping()
//     public ResponseEntity<SanPhamChiTiet> post(@RequestBody SanPhamChiTiet sanPhamChiTiet){
//         if(sanPhamChiTietDAO.existsById(sanPhamChiTiet.getIdSanPhamChiTiet())){
//             return ResponseEntity.badRequest().build();
//         }
//         sanPhamChiTietDAO.save(sanPhamChiTiet);
//         return ResponseEntity.ok(sanPhamChiTiet);
//     }

//     @PutMapping("{id}")
//     public ResponseEntity<SanPhamChiTiet> put(@PathVariable("id") Integer idSanPhamChiTiet, @RequestBody SanPhamChiTiet sanPhamChiTiet){
//         if(!sanPhamChiTietDAO.existsById(idSanPhamChiTiet)){
//             return ResponseEntity.notFound().build();
//         }
//         sanPhamChiTietDAO.save(sanPhamChiTiet);
//          return ResponseEntity.ok(sanPhamChiTiet);
//     }

//     @DeleteMapping("{id}")
//     public ResponseEntity<Void> delete(@PathVariable("id") Integer idSanPhamChiTiet){
//        if(!sanPhamChiTietDAO.existsById(idSanPhamChiTiet)){
//             return ResponseEntity.notFound().build();
//         }
//         sanPhamChiTietDAO.deleteById(idSanPhamChiTiet);
//         return ResponseEntity.ok().build();
//     }
// }
