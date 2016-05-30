## Taster
Taster is a classical web application based on the Java Servlet technology and Spring framework.
It is intended to be used for testing of candidates who apply for a job in Levi9. 

## Specification

The application consists of two main functional modules realized as two separate web applications:
1. Backend: for management of questions/answers and test templates
2. Frontend: for picking and doing the test

### Backend functionalities

1.	User story B1:

	As Administrator, I would like to manage questions in all categories.
	
	Acceptance criteria:
	*	Administrator can see all questions in all categories.
	*	Administrator can add new categories, modify existing categories and delete empty categories.
	*	Administrator can add new questions to existing categories, modify questions and delete questions.
	*	Administrator can move questions from one category to another.
	
2.	User story B2:

	As Administrator, I would like to manage test templates.
	
	Acceptance criteria:
	*	Administrator can see all test templates.
	*	Administrator can add new, modify and delete existing test templates.
	
3.	User story B3:

	As Administrator, I would like to see all the previously taken tests and the answers provided to them (historic data).
	
	Acceptance criteria:
	*	Administrator can see all the previously taken tests
	*	Administrator can search previously taken tests by candidate name
	*	Administrator can see which User initiated the test, for which hiring manager and which candidate.
	*	Administrator can see the provided answer(s), the correct answer(s) and the score for each question.
	
### Frontend functionalities

1.	User story F1:

	As User, I would like to give test to the candidate.

	Acceptance criteria:
	*	User can select one of the existing test templates
	*	User can add e-mail address of hiring manager
	*	User can add the name of the candidate
	*	User can start the test; the system prepares the test based on the selected template
	*	Candidate can answer all the questions in the test
	*	The system can evaluate the answers and store them for historic purposes
	*	The system can send an e-mail to user and hiring manager with the score per category
	
### UI mocks
TBD

## Architecture

### HLA
TBD

### Class Diagram

![alt text](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/taster-class.png)