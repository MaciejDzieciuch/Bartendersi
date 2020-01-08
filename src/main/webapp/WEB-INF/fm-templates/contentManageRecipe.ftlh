<main>


  <div class="modal fade" id="success" tabindex="-1" role="dialog"
       aria-labelledby="success" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <h3>Your Recipe was sent to the Admin for authorization</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    </div>
  </div>


  <div class="container margin_top">
    <div class="container">
      <div class="row">
        <div class="col-sm-6" id="search-container" style="margin: auto">
          <ul class="nav nav-tabs nav-justified " style="margin: 0px" id="navTab" role="tablist">
            <li class="nav-item">
              <a class="col-md text-sm-center nav-link active" id="name-tab"
                 data-target="#panel_a_name"
                 data-secondary="#panel_a_name" href="#panel_a_name"
                 data-toggle="tab"
                 role="tab"
                 aria-controls="name" aria-selected="true">
                <h4><strong>Add Recipe Form</strong></h4>
              </a>
            </li>
          </ul>

        </div>
      </div>
    </div>

    <form class="add-recipe-form margin_top" method="POST" action="#" enctype="multipart/form-data">
      <div class="row">
        <div class="card col-sm-3">


          <header class="card-header bg-dark text-light border border-secondary rounded margin_top">
            <h4 class="title">Category</h4></header>
          <div class="btn-group-vertical">
              <#list categoryList>
                  <#items as category>
                    <label class="btn btn-outline-light ">
                      <input class="x-category" type="radio" name="category"
                             style="float: left; margin: 0 auto; margin-top: 7px !important;"
                             value="${category.name}"
                             required>
                      <span class="form-check-label text-secondary"
                            style="float: left; margin-left: 10px;">${category.name}</span>
                    </label>
                  </#items>
              </#list>
          </div>
        </div>
        <div class="card col-sm-6">


          <header class="card-header bg-dark text-light border border-secondary rounded margin_top">
            <h4 class="title">Name of Drink</h4></header>
          <div class="container">

            <input type="text" id="name"
                   class="input margin_top margin_bottom border border-secondary rounded"
                   placeholder="Input Name" name="name" required
                   maxlength="30">
          </div>

          <header class="card-header bg-dark text-light border border-secondary rounded"><h4
                class="title">Ingredients</h4></header>
          <div class="container margin_top margin_bottom">
            <div class="container row" style="margin: auto; align-content: center">
              <div id="field1" style="margin: auto">
                <input autocomplete="off" class="input border border-secondary rounded" id="fielda1"
                       name="ingredient" type="text"
                       placeholder="Input ingredient" data-items="8" required/>
                <input autocomplete="off" class="finput border border-secondary rounded"
                       id="fieldb1" name="measure" type="text"
                       placeholder="Input measure" data-items="8" required/>
              </div>
              <button id="b1" class="btn btn-sm add-more btn-secondary" type="button">+</button>
            </div>
          </div>

          <header class="card-header bg-dark text-light border border-secondary rounded"><h4
                class="title">Type of Glass</h4></header>
          <div class="container">

            <input type="text" id="glass"
                   class="input margin_top margin_bottom border border-secondary rounded"
                   placeholder="Type of Glass" name="glass"
                   required
                   maxlength="30">
          </div>

          <header class="card-header bg-dark text-light border border-secondary rounded"><h4
                class="title">Instructions</h4></header>
          <div class="container">

            <input type="text" id="instructions"
                   class="input margin_top margin_bottom border border-secondary rounded"
                   placeholder="Input Instructions"
                   name="instructions" required maxlength="1000">
          </div>


        </div>
        <div class="card col-sm-3">

          <header class="card-header bg-dark text-light border border-secondary rounded margin_top">
            <h4 class="title">Type</h4></header>
          <div class="btn-group-vertical">
              <#list typeList>
                  <#items as type>
                    <label class="btn btn-outline-light ">
                      <input class="x-type" type="radio" name="type" value="${type}" required
                             style="float: left; margin: 0 auto; margin-top: 7px !important;">
                      <span class="form-check-label text-secondary"
                            style="float: left; margin-left: 10px;">${type}</span>
                    </label>
                  </#items>
              </#list>

          </div>

          <header class="card-header bg-dark text-light border border-secondary rounded margin_top">
            <h4 class="title">Image</h4></header>
          <div class="custom-file margin_top">
            <input type="file" class="custom-file-input" id="customFile">
            <label class="custom-file-label" for="customFile">Choose file</label>
          </div>
        </div>

      </div>

      <button class="btn btn-secondary btn-lg col_share" id="sub" type="submit">Add
        Recipe
      </button>
    </form>
  </div>

  <script src="/js/manageRecipe.js"></script>
</main>