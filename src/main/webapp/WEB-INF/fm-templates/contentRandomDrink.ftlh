<div class="container">
  <div class="container">
    <div class="row">
      <div class="col-sm-6 col_share" id="search-container">
        <ul class="nav nav-tabs nav-justified " style="margin: 0px" id="navTab" role="tablist">
          <li class="nav-item"><a class="col-md text-sm-center nav-link active" id="name-tab"
                                  data-target="#panel_a_name"
                                  data-secondary="#panel_a_name" href="#panel_a_name"
                                  data-toggle="tab"
                                  role="tab"
                                  aria-controls="name" aria-selected="true">
              <h4><strong>Recipe Name</strong></h4></a>
          </li>

        </ul>

        <div class="tab-content col-sm-12 " id="navTabA">

          <div class="tab-pane active" id="panel_a_name" role="tabpanel" aria-labelledby="name-tab">
            <form id="form-name">
              <input type="text" id="input-name" class="form-control" placeholder="search by name">
            </form>
          </div>
        </div>
      </div>

        <#if userType == "admin">
          <div class="col-sm-1">
            <label class="btn btn-primary btn-sm" id="del-description" title="delete"
                   style="margin-bottom: 0">
              <input class="x-delete" type="checkbox" value="${responseRecipeId.id?c}">
              <span class="form-check-label" style="padding-left: 2px"><img id="fav"
                                                                            src="https://img.icons8.com/dotty/80/000000/delete-forever.png"></span>
            </label>
          </div>
        </#if>

    </div>
  </div>

  <div class="card mb-3 margin_top">
    <div class="row no-gutters">
      <div class="col">

          <#if responseRecipeId.imageUrl??>
            <img src="${responseRecipeId.imageUrl}" style="max-width: 100%;"
                 class="card-img img-thumbnail"
                 alt="...">
          <#else>
            <img src="http://placekitten.com/g/700/700" style="max-width: 100%;"
                 class="card-img img-thumbnail"
                 alt="...">
          </#if>
      </div>
      <div class="col">
        <div class="card-body">
          <div class="row">
            <div class="col-sm-6" STYLE="margin: auto; padding-top: 24px; padding-bottom: 24px;">
              <h4 class="card-title" style="color: darkslategrey">${responseRecipeId.name}</h4>
              <div class="btn-group">
                  <#if userType !="guest">
                      <#if favouriteIdsChecked?seq_contains(responseRecipeId.id)>
                        <button class="btn btn-sm btn-outline-secondary" id="${responseRecipeId.id?c}"
                                title="remove from favourites"
                                onclick="clickedFav('#fav-image-${responseRecipeId.id?c}', '#${responseRecipeId.id?c}')">

                          <img id="fav-image-${responseRecipeId.id?c}"
                               src="https://img.icons8.com/metro/26/000000/hearts.png">

                        </button>
                      <#else>
                        <button class="btn btn-sm btn-outline-secondary" id="${responseRecipeId.id?c}"
                                title="add to favourites"
                                onclick="clickedFav('#fav-image-${responseRecipeId.id?c}', '#${responseRecipeId.id?c}')">

                          <img id="fav-image-${responseRecipeId.id?c}"
                               src="https://img.icons8.com/metro/26/000000/like.png">

                        </button>
                      </#if>
                  </#if>
              </div>
            </div>
          </div>
          <h5 class="card-title">Ingredients</h5>
          <div class="row ">
            <div class="col-md-6">
              <ul>
                  <#list responseRecipeId.ingredients>
                      <#items as ingredient>
                        <li>${ingredient.name}:</li>
                      </#items>
                  </#list>
              </ul>
            </div>
            <div class="col-md-6">
              <ul>
                  <#list responseRecipeId.ingredients>
                      <#items as ingredient>
                        <li>
                            <#if ingredient.measure != "">
                                ${ingredient.measure}
                            <#else>
                              -
                            </#if>
                        </li>
                      </#items>
                  </#list>
              </ul>
            </div>
          </div>
          <br/>
          <h5 class="card-title">Instructions</h5>
          <p class="card-text">${responseRecipeId.instruction}</p>
          <br/>
          <div class="row ">
            <div class="col-md-4">
              <h5 class="card-title">Category</h5>
              <p class="card-text">${responseRecipeId.category.name}</p>
            </div>
            <div class="col-md-4">
              <h5 class="card-title">Type</h5>
              <p class="card-text">${responseRecipeId.drinkType}</p>
            </div>
            <div class="col-md-4">
              <h5 class="card-title">Glass</h5>
              <p class="card-text">${responseRecipeId.glassType}</p>
            </div>
          </div>
          <br/>
          <p class="card-text">
            <small class="text-muted">Date of
              modification: ${responseRecipeId.modificationDate}</small>
          </p>
          <br/>
        </div>
      </div>
    </div>
  </div>

</div>
<script src="/js/recipeView.js"></script>