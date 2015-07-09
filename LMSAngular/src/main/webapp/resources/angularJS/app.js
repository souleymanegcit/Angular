var angular;
var libraryModule = angular.module('libraryApp', [ 'ngRoute', 'ngCookies', 'ngMessages' ]);

libraryModule.config([ "$routeProvider", function ($routeProvider) {
	 
	return $routeProvider.when("/", {
		redirectTo : "/home"
	}).when("/home", {
		templateUrl : "home.html"
	}).when("/listAuthors", {
		templateUrl : "listAuthors.html",
		controller: "authorCtrl"
	}).when("/publishers", {
		templateUrl : "publisher.html",
		controller: "pubCtrl"
	}).when("/genres", {
		templateUrl : "genre.html",
		controller: "genreCtrl"
	}).when("/addBook", {
		templateUrl : "addBook.html"
	}).when("/books", {
		templateUrl : "listBooks.html",
		controller: "bookCtrl"
	}).when("/test", {
		templateUrl : "test.html"
	});
	
} ]);
var console;
libraryModule.controller('homeCtrl', function ($scope, $http, $cookieStore) {
	 
	// get all authors and display initially
	$http.get('http://localhost:8080/lms/home')
		.success(
			function (data) {
				//$scope.authors = data;
				//console.log($scope.authors);
			}
		);
});
// publisher  controllers
libraryModule.controller('pubCtrl', function ($scope, $http, $cookieStore) {
	$http.get('http://localhost:8080/lms/publisher/get')
		.success(function (data) {
			$scope.publishers = data;
			//alert(JSON.stringify(data));
			console.log($scope.publishers);
		}
		    );

    $scope.addPublisher = function () {
		 
		if ($scope.addAuthorForm.$valid) {
			$http.post('author/add', {authorName: $scope.authorName}).
				success(function (data) {
					$scope.authorName = "";
	                $scope.authors = data;
			        $http.get('http://localhost:8080/lms/author/get').
								success(
		                    function (data) {
				                $scope.authors = data;
					            console.log($scope.authors);
					        }
			            );
		        }
				    );
        }
	};
				  
 
	$scope.showAddPublisher = function () {
		if ($("#addPublisherSection").css("display") === 'none') {
			$("#addPublisherSection").css("display", "block");
		} else {
			$("#addPublisherSection").css("display", "none");
		}
    };
});

// Genre controllers
libraryModule.controller('genreCtrl', function ($scope, $http, $cookieStore) {
	
	$http.get('http://localhost:8080/lms/genre/get')
		.success(
			function (data) {		
				$scope.genres = data;
			//alert(JSON.stringify(data));
				console.log($scope.genres);
			}
		);
$scope.addGenre = function addGenre() {
		if ($scope.addGenreForm.$valid) {		 
		 $http.post('genre/add', {name:$scope.name}).
		  success(function (data) {
			 alert('Genre Added');
			 $scope.name="";
			 $scope.genres = data;	
			 $http.get('http://localhost:8080/lms/genre/get')
				.success(
					function (data) {
						$scope.genres = data;
						console.log($scope.genres);
					}
				        );
   });  	
		  }
	}; 
	$scope.showAddGenre = function () { 
		if($("#addGenreSection").css("display") == 'none') {
			$("#addGenreSection").css("display", "block");
		} else {
			$("#addGenreSection").css("display", "none");
		}
	} 	    
});

// Controller to display authors
libraryModule.controller('authorCtrl',  function ($scope, $http, $cookieStore) {
	 
	$http.get('http://localhost:8080/lms/author/get')
		.success(
			function (data) {
				$scope.authors = data;
				console.log($scope.authors);
			}
		);
	$scope.showAddAuthor = function() { 
		if($("#addAuthorSection").css("display") == 'none') {
			$("#addAuthorSection").css("display", "block");
		} else {
			$("#addAuthorSection").css("display", "none");
		}
	} 	    
	
$scope.addAuthor = function addAuthor() {
		
		if($scope.addAuthorForm.$valid){
		//$scope.authors.push({'authorName':$scope.authorName,'authorId':"xxx"});
		 $http.post('author/add',{authorName:$scope.authorName}).
		  success(function (data) {
			 alert('Author Added');
			 $scope.authorName="";
			 $scope.authors = data;	
			 $http.get('http://localhost:8080/lms/author/get')
				.success(
					function (data) {
						$scope.authors = data;
						console.log($scope.authors);
					}
				);			 
		    });  		}
	}; 
	
	$scope.deleteAuthor = function deleteAuthor (a) { 
		var index = -1; 
		$http.post('author/delete', a).
		  success(function (data) {
 			 var str = a.authorName;
 			 var name= str.toUpperCase();
 		 alert('Author '  +a.authorName.toUpperCase()+ '  has been Deleted');
			 $scope.authors = data;				  
		    });			
		$scope.authors.splice(index, 1 );  		
		$http.get('http://localhost:8080/lms/author/get')
		.success(
			function (data) {
				$scope.authors = data;
				console.log($scope.authors);
			}
		);
	} ; 
	// this function is affected by Id and by Object later
	$scope.authorUpdate= function authorUpdate(b) {   
	     $("#authorName"+b.authorId).removeAttr("readonly").keyup(function() { 
	    	 $("#edit"+b.authorId).text('Update');
	    	 $("#edit"+b.authorId).click(function () { 	    	
	    		 $http.post('author/update',{authorName:$scope.authorName}).
	   		  success(function (data) {
	   			 alert('Author Updated');
	   			// $scope.authorName="";
	   			 $scope.authors = data;	
	   		  });
	    		 $("#edit"+b.authorId).text('Edit');
	    		 $http.get('http://localhost:8080/lms/author/get')
	 			.success(
	 				function (data) {
	 					$scope.authors = data;
	 					console.log($scope.authors);
	 				}
	 			); 
	    	 }); 
	}); 
	     
}
	
});
	 //Book Controllers
	libraryModule.controller('bookCtrl', function($scope, $http, $cookieStore) {
	 
		// that's to initialize all elements(AUTHOR,GENRE,PUBLISHER) to include in the book
		//$scope.books=null;
//		$http.get('http://localhost:8080/lms/book/get')
//			.success(
//				function(data) {
//					$scope.books = data;
//					console.log(data);
//					console.log($scope.books);
//				}
//			);
		$scope.bookUpdate= function bookUpdate(b) {   
		     $("#authorName"+b.authorId).removeAttr("readonly").keyup(function() { 
		    	 $("#edit"+b.authorId).text('Update');
		    	 $("#edit"+b.authorId).click(function () { 	    	
		    		 $http.post('author/update',{authorName:$scope.authorName}).
		   		  success(function (data) {
		   			 alert('Author Updated');
		   			// $scope.authorName="";
		   			 $scope.authors = data;	
		   		  });
		    		 $("#edit"+b.authorId).text('Edit');
		    		 $http.get('http://localhost:8080/lms/author/get')
		 			.success(
		 				function (data) {
		 					$scope.authors = data;
		 					console.log($scope.authors);
		 				}
		 			); 
		    	 }); 
		}); 
		     
	}
		$scope.init= function init() {
			$http.get('http://localhost:8080/lms/author/get')
			.success(
					function(data) {
						$scope.authors = data;
					}
				);
		$http.get('http://localhost:8080/lms/publisher/get')
		.success(
			function(data) {
				$scope.publishers = data;		}
		);
		$http.get('http://localhost:8080/lms/genre/get')
		.success(
			function(data) {
				$scope.genres = data;
			}
		);	
			
		}
		$scope.init();
		
		// Add book  the variable var bookElem is object like in book POJO
		$scope.addBook = function addBook() {
			var bookElem = {
					title:$scope.title,
					authors:$scope.selectedAuthors,
					publisher:$scope.selectedPub,
					genre:$scope.selectedGenres
			};
			
			console.log(bookElem);
			if($scope.addBookForm.$valid){
			 $http.post('http://localhost:8080/lms/book/add',
					  {title:$scope.title,
							authors:$scope.selectedAuthors,
							publisher:$scope.selectedPub,
							genres:$scope.selectedGenres
							
					 }).
			  success(function(data) {
				 alert('book Added');
				 $scope.title="";
				 $scope.books = data;	
				 
			    });
			}
		};
	});


 
