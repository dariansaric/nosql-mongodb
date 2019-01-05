db.news.mapReduce(
    function () {
        emit(this._id, this.comments === undefined ? 0 : this.comments.length);
    },
    function (key, values) {
        var rv = {article: key, count: 0};
        values.forEach(function (v) {
            rv.count += v;
        });

        return rv;
    },
    {out: "mr_comment_count"}
);

db.mr_comment_count.find().pretty().sort({value: -1});