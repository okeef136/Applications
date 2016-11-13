/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global index */
var i;
// Document ready function
$(document).ready(function () {
    loadDVDs();
    $('#add-button').click(function (event) {
// we don’t want the button to actually submit
// we'll handle data submission via ajax
        event.preventDefault();
        // Make an Ajax call to the server. HTTP verb = POST, URL = contact
        $.ajax({
            type: 'POST',
            url: 'dvd',
            // Build a JSON object from the data in the form
            data: JSON.stringify({
                title: $('#add-title').val(),
                director: $('#add-director').val(),
                rating: $('#add-rating').val(),
                year: $('#add-year').val(),
                note: $('#add-note').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
// If the call succeeds, clear the form and reload the summary table
            $('#add-title').val('');
            $('#add-director').val('');
            $('#add-rating').val('');
            $('#add-year').val('');
            $('#add-note').val('');
            $('#validationErrors').empty();
            loadDVDs();
            //return false;
        }).error(function (data, status) {
// #2 - Go through each of the fieldErrors and display the associated error
// message in the validationErrors div 
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });
    });

    $('#edit-button').click(function (event) {
        // prevent the button press from submitting the whole page
        event.preventDefault();

        // Ajax call -
        // Method - PUT
        // URL - contact/{id}
        // Just reload all of the Contacts upon success
        var arr = '["';
        for (d = 0; d <= i; d++) {
            if (d !== i)
            {
                var comma = '","';
                arr = arr.concat($('#edit-note' + d).val());
                arr = arr.concat(comma);
            } else {

                arr = arr.concat($('#edit-note' + d).val());
                if ($('#new-note') !== '')
                {
                    arr = arr.concat(comma);
                    arr = arr.concat($('#new-note').val());
                }
                arr = arr.concat('"]');
            }


        }
        ;
        $('#new-note').empty();
        $.ajax({
            type: 'PUT',
            url: 'dvd/' + $('#edit-dvd-id').val(),
            data: JSON.stringify({
                dvdId: $('#edit-dvd-id').val(),
                title: $('#edit-title').val(),
                director: $('#edit-director').val(),
                rating: $('#edit-rating').val(),
                year: $('#edit-year').val(),
                notes: JSON.parse(arr)


            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadDVDs();
        });
    });
});
// on click for our search button
$('#search-button').click(function (event) {
    // we don’t want the button to actually submit
    // we'll handle data submission via ajax
    event.preventDefault();
    $.ajax({
        type: 'POST',
        url: 'search/dvds',
        data: JSON.stringify({
            title: $('#search-title').val(),
            director: $('#search-director').val(),
            rating: $('#search-rating').val(),
            year: $('#search-year').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $('#search-title').val('');
        $('#search-director').val('');
        $('#search-rating').val('');
        $('#search-year').val('');
        fillDVDTable(data, status);
    });
});
//==========
// FUNCTIONS
//==========
// Load contacts into the summary table
function loadDVDs() {
    $.ajax({
        url: "dvds"
    }).success(function (data, status) {
        fillDVDTable(data, status);
    });
}

function fillDVDTable(dvdLibrary, status) {
    // Clear the previous list
    clearDVDTable();
    // Grab the tbody element that will hold the new list of contacts
    var cTable = $('#content-rows');
    // Iterate through each of the JSON objects in the test contact data
    // and render to the summary table


    $.each(dvdLibrary, function (index, dvd) {
        cTable.append($('<tr>').attr({
            style: "font:16pt"
        })
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.dvdId,
                                    'data-toggle': 'modal',
                                    'data-target': '#details-modal'

                                })
                                .text(dvd.title)
                                )
                        )
                .append($('<td>').text(dvd.director))
                .append($('<td>').text(dvd.rating))
                .append($('<td>').text(dvd.year))

                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.dvdId,
                                    'data-toggle': 'modal',
                                    'data-target': '#edit-modal'
                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteDVD(' + dvd.dvdId + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        )
                );
    });
}
// Clear all content rows from the summary table
function clearDVDTable() {
    $('#content-rows').empty();
}

function deleteDVD(id) {
    var answer = confirm("Do you really want to delete this dvd?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id
        }).success(function () {
            loadDVDs();
        });
    }
}



// This code runs in response to the show.bs.modal event - it gets the correct
// contact data and renders it to the dialog
$('#details-modal').on('show.bs.modal', function (event) {
// Get the element that triggered this event - in our case it is a contact
// name link in the summary table. This link has an attribute that contains
// the contactId for the given contact. We'll use that to retrieve the
// contact's details.
    var element = $(event.relatedTarget);
    // grab the contact id
    var dvdId = element.data('dvd-id');
    // PLACEHOLDER: Eventually we'll make an ajax call to the server to get the
    // details for this contact but for now we'll load the dummy
    // data
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-director').text(dvd.director);
        modal.find('#dvd-rating').text(dvd.rating);
        modal.find('#dvd-year').text(dvd.year);
        var noteArray = $('#dvd-notes');
        $('#dvd-notes').empty();
        $.each(dvd.notes, function (index, note) {
            noteArray.append($('<tr>')
                    .append($('<td>')
                            .text(note)));

        });

    });
});




// This code runs in response to the show.bs.modal event - it gets the correct
// contact data and renders it to the dialog
$('#edit-modal').on('show.bs.modal', function (event) {
// Get the element that triggered this event - in our case it is a contact
// name link in the summary table. This link has an attribute that contains
// the contactId for the given contact. We'll use that to retrieve the
// contact's details.
    var element = $(event.relatedTarget);
    // Grab the contact id
    var dvdId = element.data('dvd-id');
    // PLACEHOLDER: Eventually we'll make an ajax call to the server to get the
    // details for this contact but for now we'll load the dummy
    // data
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#edit-title').val(dvd.title);
        modal.find('#edit-director').val(dvd.director);
        modal.find('#edit-rating').val(dvd.rating);
        modal.find('#edit-year').val(dvd.year);
        var notesTable = modal.find('#notes-table');
        $('#notes-table').empty();
        $.each(dvd.notes, function (index, note) {
            notesTable.append($('<div>')
                    .attr({
                        class: "form-group"
                    })
                    .append($('<label>').html("Note:")
                            .attr({
                                class: "col-md-4 control-label"
                            }))
                    .append($('<div>')
                            .attr({
                                class: "col-md-8"
                            })
                            .append($('<input>')
                                    .attr({
                                        class: "form-control",
                                        id: "edit-note" + index
                                    }).val(note))));
            i = index;
        });

        modal.find('#edit-dvd-id').val(dvd.dvdId);

    });
});
