/**
 * LMS angular configurations
 */

//Routing
libraryModule.config([ "$routeProvider", function($routeProvider) {
	return $routeProvider.when("/", {
		redirectTo : "/home"
	}).when("/home", {
		templateUrl : "home.html"
	}).when("/admin", {
		templateUrl : "admin.html"
	}).when("/ListAuthors", {
		templateUrl : "listAuthors.html"
	}).when("/NewAuthor", {
		templateUrl : "addAuthor.html"
	}).when("/AddBook", {
		templateUrl : "addBook.html"
	}).when("/ListBooks", {
		templateUrl : "listbooks.html"
	}).when("/AddPublisher", {
		templateUrl : "addpublisher.html"
	}).when("/ListPublishers", {
		templateUrl : "ListPublishers.html"
	}).when("/AddBorrower", {
		templateUrl : "addborrower.html"
	}).when("/ListBorrowers", {
		templateUrl : "ListBorrowers.html"
	}).when("/Librarian", {
		templateUrl : "Librarian.html"
	});
} ]);