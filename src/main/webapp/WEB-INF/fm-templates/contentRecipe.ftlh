<main>
  <div class="container main_con drink_content margin_bottom">
    <div class="container">
      <div class="row">

        <div class="col-sm-6 col_share" id="search-container">
          <ul class="nav nav-tabs nav-justified" style="margin: 0px" id="navTab" role="tablist">
            <li class="nav-item">
              <a
                      <#if isActive?? && isActive == "ingredient">
                        class="col-md text-sm-center nav-link text-secondary"
                      <#else>
                        class="col-md text-sm-center nav-link active text-secondary"
                      </#if>
                  id="name-tab"
                  data-target="#panel_a_name"
                  data-secondary="#panel_a_name" href="#panel_a_name" data-toggle="tab" role="tab"
                  aria-controls="name" aria-selected="true">
                <h4><strong>Recipe Name</strong></h4>
              </a>
            </li>
            <li class="nav-item">
              <a
                      <#if isActive?? && isActive == "ingredient">
                        class="col-md text-sm-center nav-link active text-secondary"
                      <#else>
                        class="col-md text-sm-center nav-link text-secondary"
                      </#if>
                  type="button"
                  id="ingredient-tab"
                  data-target="#panel_a_ingredient"
                  data-secondary="#panel_a_ingredient" href="#panel_a_ingredient" data-toggle="tab"
                  role="tab"
                  aria-controls="ingredient" aria-selected="false">
                <h4><strong>Ingredient</strong></h4>
              </a>
            </li>
          </ul>

          <div class="tab-content col-sm-12" id="navTabA">
            <div
                    <#if isActive?? && isActive == "ingredient">
                      class="tab-pane"
                    <#else>
                      class="tab-pane active"
                    </#if>
                id="panel_a_name" role="tabpanel" aria-labelledby="name-tab">
              <form id="form-name">
                <input type="text" id="input-name" name="input-name" class="form-control"
                       placeholder="Search by Name">
              </form>
            </div>

            <div
                    <#if isActive?? && isActive == "ingredient">
                      class="tab-pane active"
                    <#else>
                      class="tab-pane"
                    </#if>
                id="panel_a_ingredient" role="tabpanel" aria-labelledby="ingredient-tab">
              <form id="form-ingredient">
                <input type="text" id="input-ingredient" class="form-control"
                       placeholder="Search by Ingredient">
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container" id="list-ingredient">


      <div class="pos-f-t">
        <div class="collapse" id="navbarToggleExternalContent">
          <div style="border-radius: 0.25rem; border-color: dark;" class="bg-dark p-4">

              <#include "ingredientList.ftlh">
              <#include "categoryList.ftlh">
              <#include "optionList.ftlh">
              <#include "typeList.ftlh">
            <div class="col-sm-3">
              <a style="color: #CCCECF" class="nav-link" href="/manage-recipe">Add recipe</a>
            </div>
          </div>
        </div>
        <nav style="border-radius: 0.25rem; border-color: dark;" class="navbar navbar-dark bg-dark">
          <button class="navbar-toggler" type="button" data-toggle="collapse"
                  data-target="#navbarToggleExternalContent"
                  aria-controls="navbarToggleExternalContent" aria-expanded="false"
                  aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        </nav>
      </div>
      <div class="col_drink">
          <#include "userContent.ftlh">
      </div>

    </div>
  </div>

  <script src="/js/index.js"></script>
</main>