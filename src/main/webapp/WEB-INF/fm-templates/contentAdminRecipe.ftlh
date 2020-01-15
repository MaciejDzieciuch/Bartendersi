<main>
  <div class="row margin_bottom">

    <div class="col-sm-10 white">
      <table class="table white ">
        <thead>
        <tr>
          <th scope="col">Image</th>
          <th scope="col">Name</th>
          <th scope="col">Type</th>
          <th scope="col">Category</th>
          <th scope="col">Ingredients</th>
          <th scope="col">Measures</th>
          <th scope="col">Add to drinks</th>
        </tr>
        </thead>
        <tbody>
        <#list recipesList as recipe>
          <tr>
            <td>
              <img src="${recipe.imageUrl}" style="width: 80px" title="${recipe.instruction}">
            </td>
            <td>${recipe.name}</td>
            <td>${recipe.drinkType}</td>
            <td>${recipe.category.name}</td>
            <td>
                <#list recipe.ingredients>
                    <#items as ingredient>
                      <li>${ingredient.name}</li>
                    </#items>
                </#list>
            </td>
            <td>
                <#list recipe.ingredients>
                    <#items as ingredient>
                      <li>${ingredient.measure}</li>
                    </#items>
                </#list>
            </td>
            <td>
              <div class="btn-group" role="group" aria-label="Basic example">
                <button type="button" data-id="${recipe.id?c}" class="btn btn-success yes">
                  Yes
                </button>
                <button type="button" data-id="${recipe.id?c}" class="btn btn-danger no">
                  No
                </button>
              </div>
            </td>
          </tr>
        </#list>
        </tbody>
      </table>
      <div class="row white">
        <div class="col-sm-3">
          <button class="btn btn-dark" type="button" value="1">First(1)
          </button>
          <button class="btn btn-dark" type="button"
                  <#if pageNumber gt 1> value="${pageNumber-1}" <#else> value="1" </#if>>
            &leftarrow;
          </button>
        </div>
        <div class="col-sm-6">
            <#if pageNumber gte 3>
              ...
              <button class="btn btn-secondary" type="button"
                      value='${pageNumber-2}'>${pageNumber-2}</button>
            </#if>
            <#if pageNumber gte 2>
              <button class="btn btn-secondary" type="button"
                      value='${pageNumber-1}'>${pageNumber-1}</button>
            </#if>
          <button class="btn btn-light" type="button" value='${pageNumber}'>
            <strong>${pageNumber}</strong>
          </button>
            <#if  (lastPageNumber-1) gte pageNumber>
              <button class="btn btn-dark" type="button"
                      value='${pageNumber+1}'>${pageNumber+1}</button>
            </#if>
            <#if (lastPageNumber-2) gte pageNumber>
              <button class="btn btn-dark" type="button"
                      value='${pageNumber+2}'>${pageNumber+2}</button>
              ...
            </#if>
        </div>
        <div class="col-sm-3">
          <button class="btn btn-dark" type="button"
                  <#if (lastPageNumber-1) gte pageNumber> value="${pageNumber+1}" <#else>
            value="${lastPageNumber}" </#if>>
            &rightarrow;
          </button>
          <button class=" btn btn-dark" type="button"
                  value="${lastPageNumber}">
            Last(${lastPageNumber})
          </button>
        </div>
      </div>
    </div>
  </div>
  <script src="/js/admin.js"></script>
</main>