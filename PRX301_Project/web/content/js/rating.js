var ratingValue = 0;

function chooseRating(event) {
  var firstStar = document.getElementById("star1");
  var secondStar = document.getElementById("star2");
  var lastStar = document.getElementById("star5");
  var starWidth = firstStar.getBoundingClientRect().right
          - firstStar.getBoundingClientRect().left;
  
  var firstStarPos = firstStar.getBoundingClientRect().left;
  var mousePos = event.clientX;
  var gapBetweenTwoStar = secondStar.getBoundingClientRect().left
          - firstStar.getBoundingClientRect().right;

  if (mousePos > lastStar.getBoundingClientRect().right
          || mousePos < firstStarPos) {
    return reloadRating();
  }

  var pos = (mousePos - firstStarPos) / (starWidth + gapBetweenTwoStar);
  var curStarIndex = Math.ceil(pos);
  var curStar = document.getElementById("star" + curStarIndex);
  var isFullStar = (mousePos - curStar.getBoundingClientRect().left + gapBetweenTwoStar)
          > (starWidth / 2);

  for (var i = 1; i <= 5; ++i) {
    var star = document.getElementById("star" + i);
    if (i < curStarIndex) {
      star.src = "content/img/icon/full-star.png";
    } else if (i == curStarIndex) {
      star.src = isFullStar ? "content/img/icon/full-star.png"
              : "content/img/icon/half-star.png";
    } else {
      star.src = "content/img/icon/empty-star.png";
    }
  }
  
  ratingValue = isFullStar ? curStarIndex : curStarIndex - 0.5;
}

function reloadRating() {
  var rating = document.getElementById("rating-value");
  var ratingValue = parseFloat(rating.value);
  var starIndex = Math.ceil(ratingValue);

  for (var i = 1; i <= 5; ++i) {
    var star = document.getElementById("star" + i);
    if (i < starIndex) {
      star.src = "content/img/icon/full-star.png";
    } else if (i == starIndex) {
      star.src = (starIndex == ratingValue)
              ? "content/img/icon/full-star.png"
              : "content/img/icon/half-star.png";
    } else {
      star.src = "content/img/icon/empty-star.png";
    }
  }
}

function setRating() {
  var rating = document.getElementById("rating-value");
  rating.value = ratingValue;
}