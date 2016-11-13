/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Document ready function
$(document).ready(function () {
    loadAddresses();
    $('#add-button').click(function (event) {
// we don’t want the button to actually submit
// we'll handle data submission via ajax
        event.preventDefault();
        // Make an Ajax call to the server. HTTP verb = POST, URL = contact
        $.ajax({
            type: 'POST',
            url: 'address',
            // Build a JSON object from the data in the form
            data: JSON.stringify({
                firstName: $('#add-first-name').val(),
                lastName: $('#add-last-name').val(),
                streetName: $('#add-street-name').val(),
                city: $('#add-city').val(),
                state: $('#add-state').val(),
                zipcode: $('#add-zipcode').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
// If the call succeeds, clear the form and reload the summary table
            $('#add-first-name').val('');
            $('#add-last-name').val('');
            $('#add-street-name').val('');
            $('#add-city').val('');
            $('#add-state').val('');
            $('#add-zipcode').val('');
            loadAddresses();
            //return false;
        });
    });

    $('#edit-button').click(function (event) {
        // prevent the button press from submitting the whole page
        event.preventDefault();

        // Ajax call -
        // Method - PUT
        // URL - contact/{id}
        // Just reload all of the Contacts upon success
        $.ajax({
            type: 'PUT',
            url: 'address/' + $('#edit-address-id').val(),
            data: JSON.stringify({
                contactId: $('#edit-address-id').val(),
                firstName: $('#edit-first-name').val(),
                lastName: $('#edit-last-name').val(),
                streetName: $('#edit-street-name').val(),
                city: $('#edit-city').val(),
                state: $('#edit-state').val(),
                zipcode: $('#edit-zipcode').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadAddresses();
        });
    });

    // on click for our search button
    $('#search-button').click(function (event) {
        // we don’t want the button to actually submit
        // we'll handle data submission via ajax
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'search/addresses',
            data: JSON.stringify({
                firstName: $('#search-first-name').val(),
                lastName: $('#search-last-name').val(),
                streetName: $('#search-street-name').val(),
                city: $('#search-city').val(),
                state: $('#search-state').val(),
                zipcode: $('#search-zipcode').val(),
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-first-name').val('');
            $('#search-last-name').val('');
            $('#search-street-name').val('');
            $('#search-city').val('');
            $('#search-state').val('');
            $('#search-zipcode').val('');
            fillAddressTable(data, status);
        });
    });
});
//==========
// FUNCTIONS
//==========
// Load contacts into the summary table
function loadAddresses() {
    $.ajax({
        url: "addresses"
    }).success(function (data, status) {
        fillAddressTable(data, status);
    });
}

function fillAddressTable(addressBook, status) {
    // Clear the previous list
    clearAddressTable();
    // Grab the tbody element that will hold the new list of contacts
    var cTable = $('#content-rows');
    // Iterate through each of the JSON objects in the test contact data
    // and render to the summary table

    $.each(addressBook, function (index, address) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-address-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#details-modal'
                                })
                                .text(address.firstName + ' ' + address.lastName)
                                )
                        )
                .append($('<td>').text(address.streetName))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-address-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#edit-modal'
                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteAddress(' + address.addressId + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        )
                );
    });

}
// Clear all content rows from the summary table
function clearAddressTable() {
    $('#content-rows').empty();
}

function deleteAddress(id) {
    var answer = confirm("Do you really want to delete this address?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'address/' + id
        }).success(function () {
            loadAddresses();
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
    var addressId = element.data('address-id');
    // PLACEHOLDER: Eventually we'll make an ajax call to the server to get the
    // details for this contact but for now we'll load the dummy
    // data
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {
        modal.find('#address-id').text(address.addressId);
        modal.find('#address-first-name').text(address.firstName);
        modal.find('#address-last-name').text(address.lastName);
        modal.find('#address-street-name').text(address.streetName);
        modal.find('#address-city').text(address.city);
        modal.find('#address-state').text(address.state);
        modal.find('#address-zipcode').text(address.zipcode);
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
    var addressId = element.data('address-id');
    // PLACEHOLDER: Eventually we'll make an ajax call to the server to get the
    // details for this contact but for now we'll load the dummy
    // data
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {
        modal.find('#address-id').text(address.addressId);
        modal.find('#edit-first-name').val(address.firstName);
        modal.find('#edit-last-name').val(address.lastName);
        modal.find('#edit-street-name').val(address.streetName);
        modal.find('#edit-city').val(address.city);
        modal.find('#edit-state').val(address.state);
        modal.find('#edit-zipcode').val(address.zipcode);
        modal.find('#edit-address-id').val(address.addressId);
    });
});
