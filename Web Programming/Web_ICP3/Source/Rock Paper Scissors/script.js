
        $(document).ready(function () {
            $("#rock").click(function () {
                compare("rock");
            });
            $("#paper").click(function () {
                compare("paper");
            });
            $("#scissors").click(function () {
                compare("scissors");
            });
            });
    
            function compare(choice1) {
                var computerChoice = Math.random();
                if (computerChoice < 0.34) {
                    choice2 = "rock";
    
                } else if (computerChoice <= 0.67) {
                    choice2 = "paper";
    
                } else {
                    choice2 = "scissors";
                }
    
                if (choice1 === choice2) {
                    alert( "The result is a tie!!! Lets play again.");
                }
    
                else if (choice1 === "rock") {
                    if (choice2 === "scissors") {
                        alert( "Rock wins You beat the computer, nice job!");
                    }
                    else {
                        alert("Paper wins, computer beats you.") ;
                    }
                }
    
                else if (choice1 === "paper") {
    
                    if (choice2 === "rock") {
                        alert("Paper wins  You beat the computer, nice job!") ;
                    }
                    else {
                        alert("Scissors wins, computer beat you.") ;
                    }
    
                }
    
                else if (choice1 === "scissors") {
    
                    if (choice2 === "rock") {
                        alert("Rock wins, computer beat you.") ;
    
                    }
                    else {
                        alert("Scissors win You beat the computer, nice job!") ;
                    }
                }
            } 
    