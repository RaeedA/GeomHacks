let size = window.innerWidth;

let body = document.getElementById("body");

let dimensions = document.getElementById("dimensions");
dimensions.style.marginRight = "auto";
dimensions.style.marginLeft = "auto";

let planttype = document.getElementById("planttype");
planttype.style.left = size/2 + "px";
planttype.style.marginRight = "auto";

let ok = document.getElementById("ok")
ok.style.marginLeft = (size*5)/12 + "px";
ok.style.transitionDuration = "0.4s";

document.getElementById("display").style.paddingBottom = size/25 + "px";

let confirm = function()
{
	let errors = 0;

	let length = Number(document.getElementById("length").value);
	let width = Number(document.getElementById("width").value);
	let height = Number(document.getElementById("height").value);

	if (isNaN(length) || length == 0 || isNaN(width) || width == 0 || isNaN(height) || height == 0)
	{
		errors = errors + 1;
	}

	let finish = function()
	{
		switch(errors)
		{
			case 0:
				//STUFF
				break;
			case 1:
				alert("Invalid dimensions!");
				errors=0;
				break;
			case 2:
				alert("Invalid plant type!");
				errors=0;
				break;
			case 3:
				alert("Invalid dimensions and plant type!");
				errors=0;
				break;
			default:
				break;
		}
	}

	let plant = document.getElementById("plantType").value;
	console.log(plant);
    let xhr = new XMLHttpRequest;
    let link = "http://127.0.0.1:8080/validate?plant="+plant;
    xhr.open('GET', link, true)
	xhr.onload = function() 
    {
        if (this.status === 200) 
        {
            if(this.responseText == "false")
            {
            	errors = errors + 2;
   		    }
   		    console.log(this.responseText);
   		    finish();
		}
	}

	xhr.send();
}

ok.onclick = confirm;