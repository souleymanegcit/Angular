/**
 * LMS angular controllers
 */
//controllers

libraryModule.controller('mainCtrl', function($scope, $http, $cookieStore) {


});
//book controller
libraryModule.controller('bookCtrl', function($scope, $http, $cookieStore) {
	console.log($scope.authors);
	$http.get('http://localhost:8080/lms/book/getall/1/10')
		.success(
			function(data) {
				$scope.books = data;
				console.log($scope.books);
			}
		);
	$http.get('http://localhost:8080/lms/author/getall/1/-1')
		.success(
				function(data) {
					$scope.authors = data;
				}
			);
	$http.get('http://localhost:8080/lms/publisher/getall')
	.success(
		function(data) {
			$scope.publishers = data;		}
	);
	$http.get('http://localhost:8080/lms/genre/getall')
	.success(
		function(data) {
			$scope.genres = data;
		}
	);

	
	$scope.addBook = function addBook() {
		var bookObj = {
				title:$scope.title,
				authors:$scope.selectedAuthors,
				publisher:$scope.selectedPub

		};
		
		console.log(bookObj);
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
			// showAddAuthor();
		    });
		}
	};
});

//author controller
libraryModule.controller('authorCtrl', function($scope, $http, $cookieStore) {
	// get all authors and display initially
	$http.get('http://localhost:8080/lms/author/getall/1/10')
		.success(
			function(data) {
				$scope.authors = data;
				console.log($scope.authors);
			}
		);
	
	$scope.addAuthor = function addAuthor() {
		
		if($scope.addAuthorForm.$valid){
		 $http.post('author/add',{authorName:$scope.authorName}).
		  success(function(data) {
			 alert('Author Added');
			 $scope.authorName="";
			 $scope.authors = data;	
			// showAddAuthor();
		    });
		}
	};
});

//publisher controller
libraryModule.controller('publisherCtrl', function($scope, $http, $cookieStore) {
	$http.get('http://localhost:8080/lms/publisher/getall')
		.success(
			function(data) {
				$scope.publishers = data;
				console.log($scope.publishers);
			}
		);
	
	$scope.addPublisher = function addPublisher() {
		
		//if($scope.addAuthorFrm.$valid){
		 $http.post('publisher/add',{publisherName:$scope.publisherName,publisherAddress:$scope.publisherAddress,phoneNumber:$scope.publisherPhone}).
		  success(function(data) {
			 alert('publisher Added');
			 $scope.publisherName="";
			 $scope.publisherAddress="";
			 $scope.publisherPhone="";

			 $scope.publishers = data;	
			// showAddAuthor();
		    });
		//}
	};
});

libraryModule.controller('testCtrl', function($scope, $http, $cookieStore) {
	$scope.testVar = $cookieStore.get('sample');
});

//borrower controller
libraryModule.controller('borrowerCtrl', function($scope, $http, $cookieStore) {
	$http.get('http://localhost:8080/lms/borrower/getall')
		.success(
			function(data) {
				$scope.borrowers = data;
				console.log($scope.borrowers);
			}
		);
	
	$scope.addBorrower = function addBorrower() {
		
		//if($scope.addAuthorFrm.$valid){
		 $http.post('borrower/add',{borrowerName:$scope.borrowerName,borrowerAddress:$scope.borrowerAddress,borrowerPhone:$scope.borrowerPhone}).
		  success(function(data) {
			 alert('borrower Added');
			 $scope.borrowerName="";
			 $scope.borrowerAddress="";
			 $scope.borrowerPhone="";

			 $scope.borrowers = data;	
			// showAddAuthor();
		    });
		//}
	};
});

//librarian conroller
libraryModule.controller('librarianCtrl', function($scope, $http, $cookieStore) {
	$scope.t = "dpokfp";
	$http.get('http://localhost:8080/lms/branch/getall')
	.success(
		function(data) {
			$scope.branches = data;
			console.log($scope.branches);
		}
	);
	
});
