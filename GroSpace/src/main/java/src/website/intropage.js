let padding = function() {document.getElementById("title").style.paddingTop = window.innerHeight/16+"px"};
padding();
window.onresize = padding;
document.getElementById("started").onclick = function() {window.location.href="formpage.html"};