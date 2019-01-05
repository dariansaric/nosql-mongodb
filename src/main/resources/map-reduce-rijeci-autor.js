db.news.mapReduce(
    function () { //map

        var rijeci = this.text.split(' ');
        for (var i = 0; i < rijeci.length; i++) {
            emit(this.author, rijeci[i]);
        }
    },
    function (author, rijeci) { //reduce
        var words = [];
        var map = [];

        rijeci.forEach(function (rijec) {
            if (words.indexOf(rijec) === -1) {
                words.push(rijec);
                map.push({rijec: rijec, count: 1});
            } else {
                for (var i = 0; i < map.length; i++) {
                    if (map[i].rijec === rijec) {
                        map[i].count++;
                    }
                }
            }
        });
        var comparator = (a, b) =;
    >
        a - b;
        map.sort(comparator);
        return {words: map};
    },
    {
        out: "mr_aw",
        finalize: function (author, words) { //finalize
            return words.slice(0, 11);
        }
    }
);