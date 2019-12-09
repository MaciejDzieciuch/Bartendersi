$(function () {
  $(document).ready(function () {

    $(".give").click(function () {

      $.ajax({
        url: '/api/admin/addAdminPermissions/id/' + $(this).attr('data-id'),
        type: 'PATCH',
        success: function () {
          location.reload();
        }
      });
    });

  });
});

$(function () {
  $(document).ready(function () {

    $(".revoke").click(function () {

      $.ajax({
        url: '/api/admin/revokeAdminPermissions/id/' + $(this).attr('data-id'),
        type: 'PATCH',
        success: function () {
          location.reload();
        }
      });
    });
  });
});

$(function () {
  $(document).ready(function () {

    $(".delete").click(function () {
      $.ajax({
        url: '/api/admin/users/deleteUser/id/' + $(this).attr('data-id'),
        type: 'DELETE',
        success: function () {
          location.reload();
        }
      });
    });
  });
});

$(function () {
  $(document).ready(function () {

    $(".no").click(function () {
      $.ajax({
        url: '/api/admin/recipes/deleteRecipe/id/' + $(this).attr('data-id'),
        type: 'DELETE',
        success: function () {
          location.reload();
        }
      });
    });
  });
});

$(function () {
  $(document).ready(function () {

    $(".yes").click(function () {
      $.ajax({
        url: '/api/admin/recipes/updateRecipe/id/' + $(this).attr('data-id'),
        type: 'PATCH',
        success: function () {
          location.reload();
        }
      });
    });
  });
});