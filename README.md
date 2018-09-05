# ByteGuard-Jenkins
*ByteGuard adds a human verification step to your most consequential scripts. We use a mechanism similar to multifactor authentication for soliciting approval from team members before a function executes. This functionality can be used to safeguard against human error, workflow errors, and bugs in automation.*
Below are the steps to use byteguard-build-actions plugin.
* go to link https://byteguard.io/accounts/login/
* Login with either Github or Slack
* This page will open.


* you will get an API Token with login automatically.
* you need to specify name or your task and message.
	* choose an answer format(Approval, Numeric, Text etc.)
	* choose a Consensus Rule(single,simple majority,many etc.)
	* Add people with phone or email id.
	* select a language(bash,javascript,python).
* Click on Create Task
* This will show you step4 Integrate that will show you three bash script/javascript/python codes.
	*To prompt the participants for a response, use the following code snippet:
        *To check the responses gathered so far:
	*To delete the current task:
* In these responses you will see task id of the task you have created.
* Now on Jenkins got to your project -> configure -> Add Build Step ->  ByteGuard Build Actions


* Enter your API TOKEN in Token field and Task id in Product field.
* Now people of this task will get notifications during build. 
