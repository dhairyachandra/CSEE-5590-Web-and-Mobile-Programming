function getGithubInfo(user) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
    var xhttp = new XMLHttpRequest();
    xhttp.open('GET', "https://api.github.com/users/" + user, true);
    xhttp.send();
    xhttp.onreadystatechange = function() {

        if(xhttp.readyState === 4)
        {
            if(xhttp.status === 200)
            {

                var response = JSON.parse(xhttp.responseText);
                console.log(response);
                showUser(response);
            }
            else {
                noSuchUser(username);
            }
        }
    };
}

function showUser(user) {
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    $("#profilePic").html("<img height='200' width='200' src='"+ user.avatar_url+"'/>");
    $("#name").text('User Name : '+user.login);
    $("#Id").text('Profile Id : '+user.id);
    $("#location").text('Location : '+user.location);
    $("#followers").text('Followers : '+user.followers);
    $("#following").text('Following : '+user.following);
    $("#link").html("<a href='"+user.html_url+"' target='_blank'>Link URL</a>");
}

function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed
    alert("user selected " + username + " is not present");
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse
            response = getGithubInfo(username);
            //if the response is successful show the user's details
            if (response.status == 200) {
                showUser(JSON.parse(response.responseText));
                //else display suitable message
            } else {
                noSuchUser(username);
            }
        }
    })
});
