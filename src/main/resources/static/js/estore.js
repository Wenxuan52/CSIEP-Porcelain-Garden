$(function () {
  $('img.logo').click(function () {

    console.log('跳转首页')
    window.location.href="toIndex"
  })
  $('.type').mouseenter(function () {
        $(".type-list").removeClass("hide")
      }
  );



  $(".type-list").mouseleave(function () {
    $(".type-list").addClass("hide")
  });

  $(".list-group-item").mouseenter(function () {
    var v = $(".list-group").height();
    $(".box").height(v)

    $(".box").removeClass("hide")
    var innerHTML = $(this).children('.list-group-item-item').html()
    $(".box ul").html(innerHTML)
    var l = $(".box").children('.list-group-item-item').removeClass("hide");


  })



  $('.carousel').carousel({
    interval: 3000,
  })

  $('.viewbook .book-message #box dd').click(function () {
    $('.viewbook .book-message #box dd').removeClass('active')
    $(this).addClass('active')
  })
  $('#typeMenu').collapse('hide')
  $('.col-md-3').addClass('col-sm-3 col-xs-3')
  $('.col-md-6').addClass('col-sm-6 col-xs-6')
  $('.col-md-9').addClass('col-sm-9 col-xs-9')
  $('.col-md-2').addClass('col-sm-2 col-xs-2')
  $('.col-md-8').addClass('col-sm-8 col-xs-8')
  var twoDiv = $('.bigDiv').children()
  twoDiv.each(function () {
    $(this).css('border-bottom', '1px solid var(--color)')
  })
  twoDiv.last().css('border-bottom', 'none')


})

function checkLogin() {
  console.log('this is  check login')
  var username = $('#username').val()
  var password = $('#password').val()
  if (username == '') {
    alert('用户名不能为空！')
    return false
  }
  if (password == '') {
    alert('密码不能为空！')
    return false
  }
  return true
}
function checkReg() {
  if ($('#reg-login-name').val() == '') {
    alert('登录名不能为空')
    return false
  }
  if ($('#reg-nick-name').val() == '') {
    alert('用户名不能为空')
    return false
  }

  if ($('#reg-pwd').val() == '') {
    alert('密码不能为空')
    return false
  }
  if ($('#reg-address').val() == '') {
    alert('地址不能为空')
    return false
  }
  if ($('#reg-phone').val() == '') {
    alert('电话不能为空')
    return false
  }
  if ($('#reg-emial').val() == '') {
    alert('邮箱不能为空')
    return false
  }
  if (!$('#tip-box').is(':checked')) {
    alert('请先同意隐私协议')
    return false
  }

  return checkPwd()
}
function checkPwd() {
  console.log($('#reg-pwd').val())
  if ($('#reg-pwd').val() != $('#reg-again-pwd').val()) {
    $('#tip').text('两次密码输入不一致')
    $('#tip').show()
    return false
  } else {
    $('#tip').hide()
    return true
  }
}
function searchShop() {
  const name = $('#searchShopName').val();
  console.log($('#searchShopName').val())
  window.location.href = "searchShop?shopName="+name;

}
function addShopCar(bookid,user) {
  $.ajax({
    type: 'get',
    url: 'addShopCar?shopId=' + bookid,
    success: function (data) {
      if (data!=""){
        $('html').html(data)
      }else{
        alert(" 添加购物车成功")
      }
    },
    error: function () {
      alert("添加购物车失败")
    },
  })

}





