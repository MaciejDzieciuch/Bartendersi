<main>
  <h2 style="text-align: center">Load drinks from JSON file</h2>
  <div class="container main_con">
    <form id="form-upload" action="/admin/upload-data" method="post" enctype="multipart/form-data">
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <button class="btn btn-outline-secondary" type="submit" id="inputGroupFileAddon03">Load
            data
          </button>
        </div>
        <div class="custom-file">
          <input type="file" name="drinks" class="custom-file-input" id="inputGroupFile03"
                 aria-describedby="inputGroupFileAddon03">
          <label class="custom-file-label" for="inputGroupFile03">Choose file</label>
        </div>
      </div>
    </form>
  </div>
  <script src="/js/uploadJson.js"></script>
</main>