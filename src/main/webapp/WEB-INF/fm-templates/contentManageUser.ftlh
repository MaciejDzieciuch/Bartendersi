<main>
  <div class="container main_con">
  <div class="col-sm-10 white">
    <table class="table white ">
      <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">User Type</th>
        <th scope="col">Admin privileges</th>
        <th scope="col">Delete</th>
      </tr>
      </thead>
      <tbody>
      <#list usersPerPage as user>
        <tr>
          <td>${user.name}</td>
          <td>${user.email}</td>
          <td>${user.userType}</td>
            <#if user.id != 1>

              <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                  <button type="button" data-id="${user.id}" class="btn btn-success give">
                    GIVE
                  </button>
                  <button type="button" data-id="${user.id}"
                          class="btn btn-danger revoke">
                    REVOKE
                  </button>
                </div>
              </td>
              <td>
                <div>
                  <button type="button" data-id="${user.id}" class=" btn btn-delete delete">
                    DELETE
                  </button>
                </div>
              </td>
            </#if>
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
  <script src="/js/admin.js"></script>
  </div>

</main>