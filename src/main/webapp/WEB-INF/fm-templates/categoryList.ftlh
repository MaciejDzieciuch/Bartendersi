<header>
  <h4 class="title" style="color: #AAC7CF">Category</h4>
</header>
<div>
    <#list categoryList>
        <#items as category>
          <label class="btn btn-outline-light">
            <input class="x-category" type="checkbox" name="myradio" value="${category.id}"
                   onclick="checkFilters()" ${categoryListChecked?seq_contains("${category.id}")?string("checked", "")}>
            <span style="color: #AAC7CF" class="form-check-label">${category.name}</span>
          </label>
        </#items>
    </#list>
</div>