<main>
  <div class="container main_con drink_content">
    <div class="container">
      <div class="row">
        <div class="col-sm-6" id="search-container">
          <ul class="nav nav-tabs nav-justified" style="margin: 0px" id="navTab" role="tablist">
            <li class="nav-item">
              <a
                      <#if isActive?? && isActive == "ingredient">
                        class="col-md text-sm-center nav-link"
                      <#else>
                        class="col-md text-sm-center nav-link active"
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
                        class="col-md text-sm-center nav-link active"
                      <#else>
                        class="col-md text-sm-center nav-link"
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
                       placeholder="search by name">
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
                       placeholder="search by ingredient">
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container" id="list-ingredient">

      <div class="row">
        <div class="col_category col border border-dark rounded">
            <#include "ingredientList.ftlh">
            <#include "categoryList.ftlh">
            <#include "optionList.ftlh">
            <#include "typeList.ftlh">
        </div>
        <div class="col_drink">
            <#include "userContent.ftlh">
        </div>
      </div>
    </div>
  </div>

  <script src="/js/index.js"></script>
</main>