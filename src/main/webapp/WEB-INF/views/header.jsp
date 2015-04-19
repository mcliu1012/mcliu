<%@ page contentType='text/html;charset=UTF-8' pageEncoding='UTF-8'%>
<link rel="stylesheet" type="text/css" href="/resources/css/header.css" />
<script src="/resources/js/header.js"></script>
<div>
  <div id="pHeaderImgDiv" class="visible-lg-inline-block visible-md-inline-block">
    <img id="pHeaderImg" src="/resources/img/logo.png" />
  </div>
  <div id="pHeaderShowHideP" class="inlineBlock cursorPointer"><p class="inlineBlock"><span class="icon-reorder icon-2x"></span></p></div>
  <div id="pHeaderMsgDiv" class="visible-lg-inline-block visible-md-inline-block visible-sm-inline-block">MCLIU’s Personal Website</div>
  <div class="pHeaderMsgDiv visible-xs-inline-block">liujiaweb</div>
  <div id="pHeaderLogoutDiv" class="inlineblock">
    <a class="btn logoutA btn-primary" href="<c:url value='/logout' />"><span class="icon-reply"></span> 退出</a>
  </div>
</div>