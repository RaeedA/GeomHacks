let xhr = new XMLHttpRequest;
let link = "http://127.0.0.1:8080/image";
xhr.open('GET', link, true)

xhr.onload = function()
{
  if (this.status === 200) 
  {
    document.getElementById("image").src="data:image/png;base64,"+this.responseText;
	}
}
xhr.send();

let xhr1 = new XMLHttpRequest;
let link1 = "http://127.0.0.1:8080/number";
xhr1.open('GET', link1, true)

xhr1.onload = function()
{
  if (this.status === 200) 
  {
    document.getElementById("plants").innerHTML=this.responseText;
  }
}
xhr1.send();

let xhr2 = new XMLHttpRequest;
let link2 = "http://127.0.0.1:8080/electricity";
xhr2.open('GET', link2, true)

xhr2.onload = function()
{
  if (this.status === 200) 
  {
    document.getElementById("electricity").innerHTML=this.responseText;
  }
}
xhr2.send();

let xhr3 = new XMLHttpRequest;
let link3 = "http://127.0.0.1:8080/water";
xhr3.open('GET', link3, true)

xhr3.onload = function()
{
  if (this.status === 200) 
  {
    document.getElementById("water").innerHTML=this.responseText;
  }
}
xhr3.send();