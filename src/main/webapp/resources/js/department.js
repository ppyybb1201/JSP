var deptService = (function() {
	function getDeptList(callback, error) {
		$.getJSON(
			"/department",
			function(data) {
				if (callback) {
					callback(data);
				}
			}
		).fail(function(xhr, status, err) {
			if (error) {
				error(err);
			}
		});
	}
	function getDeptTitleList(dept_no, callback, error) {
		$.getJSON(
			"/department/titles?dept_no=" + dept_no,
			function(data) {
				if (callback) {
					callback(data);
				}
			}
		).fail(function(xhr, status, err) {
			if (error) {
				error(err);
			}
		});
	}
	return {
		getDeptList: getDeptList,
		getDeptTitleList: getDeptTitleList
	};
})();