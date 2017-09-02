$(function() {
  'use strict';

  function setMessage(klass, opt) {
    $(".help-block > span").hide();
    $(".help-block ." + klass).show();
    $(".help-block ." + klass + " .opt").text(opt || '');
  }

  $('form').on('submit', function (e) {
    e.stopPropagation();
    e.preventDefault();

    setMessage('progress');
    $('#email').prop('disabled', true);

    $.ajax('/invite', {
      method: 'POST',
      data: { email: $('#email').val() },
    })

    .done(function (data) {
      console.log(data)
      console.log(data.error)

      if (data && data.ok) {
        setMessage('succeeded');
        $('#email').val('');
      }
      else if (data.error) {
        setMessage('failed', '(' + data.error + ')');
      }
      else {
        setMessage('failed');
      }
    })

    .fail(function () {
      setMessage('failed');
    })

    .always(function () {
      $('#email').prop('disabled', false);
    });
  });
});
