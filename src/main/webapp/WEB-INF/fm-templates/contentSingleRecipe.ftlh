<div class="container">
  <div class="container">
    <div class="row">
        <#--      <div class="col-sm-3">-->

        <#--          <#if userType!="guest">-->
        <#--              <#if isFavourite == true>-->
        <#--                <button class="btn btn-secondary btn-sm" id="fav-description"-->
        <#--                       title="remove from favourites"-->
        <#--                       style="margin-bottom: 0" value="${responseRecipeId.id?c}">-->

        <#--                  <span class="form-check-label" style="padding-left: 2px">-->
        <#--                    <img id="fav" src="https://img.icons8.com/metro/80/000000/hearts.png"></span>-->
        <#--                </button>-->
        <#--              <#else>-->
        <#--                <button class="btn btn-secondary btn-sm" id="fav-description" title="add to favourites"-->
        <#--                       style="margin-bottom: 0" value="${responseRecipeId.id?c}">-->

        <#--                  <span class="form-check-label" style="padding-left: 2px">-->
        <#--                    <img id="fav" src="https://img.icons8.com/metro/80/000000/like.png"></span>-->
        <#--                </button>-->
        <#--              </#if>-->
        <#--          </#if>-->
        <#--      </div>-->
        <#--      serduszko do ulubionych-->
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
        <#-- wyszukiwanie -->
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
    <#--cos dla admina-->


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

                  <#if userType!="guest">
                      <#if isFavourite == true>
                        <button class="btn btn-sm btn-outline-secondary" id="fav-description"
                                title="remove from favourites"
                                style="margin-bottom: 0" value="${responseRecipeId.id?c}">
                          <img id="fav"
                               src="https://img.icons8.com/metro/80/000000/hearts.png"></span>
                        </button>
                      <#else>
                        <button class="btn btn-sm btn-outline-secondary" id="fav-description"
                                title="add to favourites"
                                style="margin-bottom: 0" value="${responseRecipeId.id?c}">
                          <img id="fav"
                               src="https://img.icons8.com/metro/80/000000/like.png"></span>
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


    <#--  <div class="col-sm-6" STYLE="margin: auto; padding-top: 24px; padding-bottom: 24px;">-->
    <#--    <h2 class="card-title" style="color: olivedrab">${responseRecipeId.name}</h2>-->
    <#--  </div>-->

    <#--  <div class="col-sm-10">-->

    <#--    <div class="row ">-->
    <#--      <div class="col-md-6" style="margin:0px">-->
    <#--          <#if responseRecipeId.imageUrl??>-->
    <#--            <img src="${responseRecipeId.imageUrl}" style="max-width: 100%;"-->
    <#--                 class="card-img"-->
    <#--                 alt="...">-->
    <#--          <#else>-->
    <#--            <img src="http://placekitten.com/g/700/700" style="max-width: 100%;"-->
    <#--                 class="card-img"-->
    <#--                 alt="...">-->
    <#--          </#if>-->
    <#--      </div>-->
    <#--      <div class="col-md-6">-->
    <#--        <div class="card-body">-->
    <#--          <h5 class="card-title">Ingredients</h5>-->
    <#--          <div class="row ">-->
    <#--            <div class="col-md-6">-->
    <#--              <ul>-->
    <#--                  <#list responseRecipeId.ingredients>-->
    <#--                      <#items as ingredient>-->
    <#--                        <li>${ingredient.name}:</li>-->
    <#--                      </#items>-->
    <#--                  </#list>-->
    <#--              </ul>-->
    <#--            </div>-->
    <#--            <div class="col-md-6">-->
    <#--              <ul>-->
    <#--                  <#list responseRecipeId.ingredients>-->
    <#--                      <#items as ingredient>-->
    <#--                        <li>-->
    <#--                            <#if ingredient.measure != "">-->
    <#--                                ${ingredient.measure}-->
    <#--                            <#else>-->
    <#--                              --->
    <#--                            </#if>-->
    <#--                        </li>-->
    <#--                      </#items>-->
    <#--                  </#list>-->
    <#--              </ul>-->
    <#--            </div>-->
    <#--          </div>-->
    <#--          <br/>-->
    <#--          <h5 class="card-title">Instructions</h5>-->
    <#--          <p class="card-text">${responseRecipeId.instruction}</p>-->
    <#--          <br/>-->
    <#--          <div class="row ">-->
    <#--            <div class="col-md-4">-->
    <#--              <h5 class="card-title">Category</h5>-->
    <#--              <p class="card-text">${responseRecipeId.category.name}</p>-->
    <#--            </div>-->
    <#--            <div class="col-md-4">-->
    <#--              <h5 class="card-title">Type</h5>-->
    <#--              <p class="card-text">${responseRecipeId.drinkType}</p>-->
    <#--            </div>-->
    <#--            <div class="col-md-4">-->
    <#--              <h5 class="card-title">Glass</h5>-->
    <#--              <p class="card-text">${responseRecipeId.glassType}</p>-->
    <#--            </div>-->
    <#--          </div>-->
    <#--          <br/>-->
    <#--          <p class="card-text">-->
    <#--            <small class="text-muted">Date of-->
    <#--              modification: ${responseRecipeId.modificationDate}</small>-->
    <#--          </p>-->
    <#--          <br/>-->
    <#--        </div>-->
    <#--      </div>-->
    <#--    </div>-->
    <#--  </div>-->

</div>
<script src="/js/recipeView.js"></script>