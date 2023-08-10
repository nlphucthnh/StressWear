function increaseCount(event, element) {
    var input = element.parentElement.querySelector('input');
    var value = parseInt(input.getAttribute('data-quantity'), 10);
    value = isNaN(value) ? 0 : value;
    value++;
    input.setAttribute('data-quantity', value);
    input.value = value;
  }

  function decreaseCount(event, element) {
    var input = element.parentElement.querySelector('input');
    var value = parseInt(input.getAttribute('data-quantity'), 10);
    if (value > 1) {
      value = isNaN(value) ? 0 : value;
      value--;
      input.setAttribute('data-quantity', value);
      input.value = value;
    }
  }