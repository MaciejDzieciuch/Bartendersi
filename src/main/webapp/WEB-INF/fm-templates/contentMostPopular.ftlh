<h2 align="center">Top ten most popular recipes</h2>
<#list recipeList>
  <div class="row">
    <#items as recipe>
      <div class=" col-md-3">
        <div class="card mb-3 shadow-sm">


            <#if recipe.imageUrl??>
            <#else>
              <img src="http://placekitten.com/500/500">
            </#if>
          <img src="${recipe.imageUrl}" class="card-img" alt="...">


          <div class="card-body">
            <p class="font-weight-bold text-uppercase" style="color: #1d2124">${recipe.name}</p>


            <div class="d-flex justify-content-between align-items-center">
              <a href="/recipeView?recipeId=${recipe.id?c}">
                <div class="btn-group">

                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
              </a>
            </div>
              <#if userType !="guest">
                  <#if favouriteIdsChecked?seq_contains(recipe.id)>
                    <button class="btn btn-sm btn-outline-secondary" id="${recipe.id?c}"
                            title="remove from favourites"
                            onclick="clickedFav('#fav-image-${recipe.id?c}', '#${recipe.id?c}')">

                      <img id="fav-image-${recipe.id?c}"
                           src="https://img.icons8.com/metro/26/000000/hearts.png">

                    </button>
                  <#else>
                    <button class="btn btn-sm btn-outline-secondary" id="${recipe.id?c}"
                            title="add to favourites"
                            onclick="clickedFav('#fav-image-${recipe.id?c}', '#${recipe.id?c}')">

                      <img id="fav-image-${recipe.id?c}"
                           src="https://img.icons8.com/metro/26/000000/like.png">

                    </button>
                  </#if>
              </#if>
              <#if userType == "admin">
                <button class="btn btn-sm btn-outline-secondary" data-id="${recipe.id?c}"
                        title="delete">

                  <img
                      src="https://img.icons8.com/dotty/26/000000/delete-forever.png">

                </button>
              </#if>

          </div>
        </div>


      </div>
      </div>
    </#items>
  </div>
</#list>
<script src="/js/index.js"></script>