var slidList = document.querySelector('#slideList');
var imgs = document.querySelectorAll('.imgs');
var imgLength = imgs.length;
var imgWidth = imgs[0].width;
var left = document.querySelector('#left');
var right = document.querySelector('#right');
var currImg = 0;
if (imgLength > 1) {
  init();
 
}
function init() {
	
  addImages();
  addClickEvent();

}

function addClickEvent() {
  left.addEventListener('click', () => {
    moveSlide(currImg - 1);
  });
  right.addEventListener('click', () => {
    moveSlide(currImg + 1);
  });

  $('#slideList').click(function (event) {
    if (event.offsetX >= 210) {
      moveSlide(currImg + 1);
    } else {
      moveSlide(currImg - 1);
    }
  });
}
function addImages() {
  //맨 뒤 그림부터 앞에 추가
  for (let i = imgLength - 1; i >= 0; i--) {
    var cloneImg = imgs[i].cloneNode(true);
    cloneImg.classList.add('clone');
    slidList.prepend(cloneImg);
  }
  //맨 앞 그림부터 뒤에 추가
  for (let i = 0; i < imgLength; i++) {
    var cloneImg = imgs[i].cloneNode(true);
    cloneImg.classList.add('clone');
    slidList.append(cloneImg);
  }
  //굳이 이렇게 10개를 두는 이유는 이래야 위치 잡기 편함
  updateSlideWidth();
  setInitPos();
}

function updateSlideWidth() {
  var newimgs = document.querySelectorAll('.imgs');
  var newLen = newimgs.length;
  slidList.style.width = newLen * imgWidth + 'px';
}

function setInitPos() {
  var initpos = -(imgLength * imgWidth);
  slidList.style.transform = 'translateX(' + initpos + 'px)';
  setTimeout(() => {
    slidList.classList.add('active');
  }, 100);
}

function moveSlide(num) {
  var left = -(num * imgWidth);
  slidList.style.left = left + 'px';
  currImg = num;
  console.log("num="+num);

  if (currImg === imgLength || -currImg === imgLength) {
  		
    let timeout1 = setTimeout(() => {
      slidList.classList.remove('active');
      slidList.style.left = 0;
      currImg = 0;
    }, 300);
    
   let timeout2 = setTimeout(() => {
      slidList.classList.add('active');
    }, 350);
  
  }
}

function timeOut(){
	console.timeEnd();
}