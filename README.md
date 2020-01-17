# play-filter-only

Play's filter is basically applied to all requests. Sometimes I want to apply a
filter to only specific paths. `PlayFilterOnly` wraps a filter, and selectively apply
an underlying filter by given predicate.

To use play-filter-only, add a following line to `build.sbt` (for Play 2.8.x).

    libraryDependencies += "com.github.tkawachi" %% "play-filter-only" % "0.4.0"

See the following table if you're using an older version of Play.

| Play version | Play-filter-only version |
| ------------ | ------------------------ |
| 2.8.x        | 0.4.0                    |
| 2.7.x        | 0.3.0                    |
| 2.6.x        | 0.2.0                    |
| 2.5.x        | 0.1.0                    |
| 2.4.x        | 0.0.2                    |

For example, to create a `CSRFFilter` which is only applied to paths starting with
`/foo`.

```scala
import com.github.tkawachi.PlayFilterOnly._
CSRFFilter().only(_.path.startsWith("/foo"))
```

`only()` takes a function to choose which request to apply a filter.

See http://qiita.com/kawachi/items/1e74647ffe1993b70a62 (Japanese).

This software is released under the MIT License.
