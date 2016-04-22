# GitHub Guide
## Create Branch
* Create a new branch for your own development environment by clicking on "Branch: Master" button on the left upper corner in the main repository page by giving a specific name such as "mfs-dev" for 'M'ehmet 'F'urkan 'S'ahin - 'Dev'elopment.

## Setup the repository on your computer
* Click on the "Save <Repository>" button on the right upper corner in the main repository page just near the "Download ZIP" button.
 * If you do not have GitHub Desktop Application on your computer installed, this click will start a setup process for GitHub Desktop.
 * If it is already installed, you will be redirected to the application and it will ask you to provide a location to setup the repository on your computer.
* From now on, you have the repository on your computer and you can start to code!! Yeeeyyy...
* Before starting, do not forget to change the active branch from master to your own branch that you named it. (e.g. mfs-dev)
* Now, you have the repository and you can start to work on your own branch. 
* Start coding immediately, we do not have any second to lose!
* Frequent committing is very important to avoid huge conflicts.

## Commit
* When you code a specific part (e.g. when you believe that a method you have just coded is done), you can commit it to your own branch. 
* To commit, you should go to GitHub Desktop application and there, you will see the changes you made to the code. 
 * You should give a summary before committing. 
 * Be sure that the summary you give explains what you did in a short manner. 
 * Click on the commit button.
 * Click on the sync button on the right upper corner.
* After committing it to your own branch, be sure that it does not crash and causes any error on the code. 
 * This is important because we always want to keep the master branch healthy since everyone uses it frequently it is very important that your other friends' code will not crash because of a code block that you wrote.
* After being sure that it does not produce any error, you should update your branch from master if any of your other friends committed to the master branch.

## Update your branch
* You should first click on the compare button from GitHub Application and choose the "master" branch.
* If it is not disabled, you should choose "update from master"
* If it is disabled, that means there is no commit made by your friends and you can simply click on the commit button. However, there is still a chance that your code my conflict with the master branch and you should follow the above part 

## Resolve Conflicts
* After updating your branch if there is any conflict, GitHub will give you an error message and sign your files that are conflicting with the master branch.
* The conflicting files signed with yellow on the changes column in the GitHub Desktop Application.
* For each of these files, you should click right and choose open with external editor.
 * When you open the file, you will see GitHub's tagging as follows;
  * When two parts conflicts, the part that is in your branch will be starting with "<<<<<<< HEAD"
  * The part that is in your branch will end with "=======" and the part that is coded by your friend will start under this sign.
  * The part that is coded by your friend will end with ">>>>>>> origin/master"
 * In this stage, you should look at the code and communicate with your friend immediately before making any change if you do not know what the code that your friend wrote does.
 * After communicating, you should choose how to merge to parts by removing the tags that is put by GitHub. 
  * You may decide to remove one part completely or try to merge them, it is completely up to the functionalities that you want to keep.
* After resolving all of the conflicts, you are ready to merge your branch. You can simply click on the "Commit" button.
