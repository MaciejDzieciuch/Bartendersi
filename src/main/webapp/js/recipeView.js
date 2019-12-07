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
