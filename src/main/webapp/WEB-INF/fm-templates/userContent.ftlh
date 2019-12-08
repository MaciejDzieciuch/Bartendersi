<#list recipeListPerPage>
  <div class="row">
    <#items as recipe>
      <div class="col border border-dark rounded">
        <a href="/recipeView?recipeId=${recipe.id?c}">
          <div class="col">
            <div class="border border-dark rounded">
              <p>${recipe.name}</p>
            </div>
            <#if recipe.imageUrl??>
            <#else>
              <img src="http://placekitten.com/500/500">
            </#if>
            <img src="${recipe.imageUrl}" class="card-img" alt="...">
          </div>
          <div class="col">
            <div>

              <div class="col">
                <#if userType !="guest">
                  <#if favouriteIdsChecked?seq_contains(recipe.id)>
                          <button class="btn btn-primary btn-sm x-fav" id="${recipe.id?c}"
                                  title="remove from favourites"
                                  onclick="clickedFav('#fav-image-${recipe.id?c}', '#${recipe.id?c}')">
                            <img id="fav-image-${recipe.id?c}"
                                 src="https://img.icons8.com/metro/26/000000/hearts.png">
                          </button>
                        <#else>
                          <button class="btn btn-primary btn-sm x-fav" id="${recipe.id?c}"
                                  title="add to favourites"
                                  onclick="clickedFav('#fav-image-${recipe.id?c}', '#${recipe.id?c}')">
                            <img id="fav-image-${recipe.id?c}"
                                 src="https://img.icons8.com/metro/26/000000/like.png">
                          </button>
                        </#if>
                    </#if>
                    <#if userType == "admin">
                      <button class="btn btn-primary btn-sm x-fav del" data-id="${recipe.id?c}"
                              title="delete">
                        <img
                            src="https://img.icons8.com/dotty/26/000000/delete-forever.png">
                      </button>
                    </#if>
                </div>
              </div>
            </div>
          </div>
        </a>
      </div>
    </#items>
  </div>
</#list>

<div class="row paginator">
  <div class="col-sm-3 paginator">
    <button class="favorite btn btn-primary btn-sm" type="button" value="1">First(1)</button>
    <button class="favorite btn btn-primary btn-sm" type="button"
            <#if pageNumber gt 1> value="${pageNumber-1}" <#else> value="1" </#if>> &leftarrow;
    </button>
  </div>
  <div class="col-sm-6 paginator">
      <#if pageNumber gte 3>
        ...
        <button class="favorite btn btn-primary btn-sm" type="button"
                value='${pageNumber-2}'>${pageNumber-2}</button>
      </#if>
      <#if pageNumber gte 2>
        <button class="favorite btn btn-primary btn-sm" type="button"
                value='${pageNumber-1}'>${pageNumber-1}</button>
      </#if>
    <button class="favorite btn btn-light btn-sm" id="page-number" type="button"
            value='${pageNumber}'>
      <strong>${pageNumber}</strong>
    </button>
      <#if  (lastPageNumber-1) gte pageNumber>
        <button class="favorite btn btn-primary btn-sm" type="button"
                value='${pageNumber+1}'>${pageNumber+1}</button>
      </#if>
      <#if (lastPageNumber-2) gte pageNumber>
        <button class="favorite btn btn-primary btn-sm" type="button"
                value='${pageNumber+2}'>${pageNumber+2}</button>
        ...
      </#if>
  </div>
  <div class="col-sm-3 paginator">
    <button class="favorite btn btn-primary btn-sm" type="button"
            <#if (lastPageNumber-1) gte pageNumber> value="${pageNumber+1}" <#else> value="${lastPageNumber}" </#if>>
      &rightarrow;
    </button>
    <button class="favorite btn btn-primary btn-sm" type="button" value="${lastPageNumber}">
      Last(${lastPageNumber})
    </button>
  </div>
</div>