<div class="modal fade" id="details-modal" tabindex="-1" role="dialog"
     aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="detailsModalLabel">DVD Details</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <tr>
                        <th>Title:</th>
                        <td id="dvd-title"></td>
                    </tr>
                    <tr>
                        <th>Director:</th>
                        <td id="dvd-director"></td>
                    </tr>
                    <tr>
                        <th>Rating:</th>
                        <td id="dvd-rating"></td>
                    </tr>
                    <tr>
                        <th>Year:</th>
                        <td id="dvd-year"></td>
                    </tr>
                    <tr>
                    <th>Notes:</th>
                    <td id="dvd-notes"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Close
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Edit Modal -->
<div class="modal fade" id="edit-modal" tabindex="-1" role="dialog"
     aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="details-modal-label">Edit DVD</h4>
            </div>
            <div class="modal-body">
                <h3 id="dvd-id"></h3>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="edit-title" class="col-md-4 control-label">
                            Title:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-title"
                                   placeholder="Title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-director" class="col-md-4 control-label">
                            Director:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-director"
                                   placeholder="Director">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-rating" class="col-md-4 control-label">
                            Rating:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-rating"
                                   placeholder="Rating">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-year" class="col-md-4 control-label">
                            Year:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-year"
                                   placeholder="Year">
                        </div>
                    </div>
                    

                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="edit-button" class="btn btn-default"
                                    data-dismiss="modal">
                                Edit DVD
                            </button>
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">
                                Cancel
                            </button>
                            <input type="hidden" id="edit-dvd-id">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
