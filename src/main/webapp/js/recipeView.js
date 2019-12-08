const $formName = $('#form-name');

let listOfNames = [];

$formName.on('submit', () => {
  const $input = $("#input-name");
  message = $input.val();
  if (message.length === 0) {
    return false;
  }
  if (listOfNames.some(recipe => recipe.name === $input.val())) {
    let recipeIDs = listOfNames.filter(recipe => recipe.name === $input.val());
    window.location = '/recipeView?recipeId=' + recipeIDs[0].id;
  }
  $input.val('');
  return false;
});

$('#input-name').keyup(function () {
  if (this.value.length < 3) {
    return;
  }
  var substring = $(this).val();

  $.ajax({
    url: '/api/recipes/nameChars/' + substring,
    type: 'GET',
    success: function (data) {
      listOfNames = data;
      let result = data.map(r => r.name);
      $("#input-name").autocomplete({
        source: result
      });
    }
  });
});

$('#del-description').click(function () {
  var favToBeChangedId = $('.x-delete').val();

  $.ajax({
    url: '/api/admin/recipes/deleteRecipe/' + favToBeChangedId,
    type: 'DELETE',
    success: function () {
      window.location = document.referrer;
    }
  });
});

$('#fav').click(function () {

  if ($(this).attr("src")
      === "https://img.icons8.com/metro/80/000000/like.png") {
    $(this).attr("src", "https://img.icons8.com/metro/80/000000/hearts.png");
    $("#fav-description").attr('title', "Remove from favourites")
  } else {
    $(this).attr("src", "https://img.icons8.com/metro/80/000000/like.png");
    $("#fav-description").attr('title', "Add to favourites")
  }

  var favToBeChangeId = $('.x-favourite').val();

  $.ajax({
    url: '/api/favourites/' + favToBeChangeId,
    type: 'GET',
    success: function () {
      console.log("Favourites recipes changed");
    }
  });
});