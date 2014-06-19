# dependency-repository-web

The basic idea is to provide a search mechanism for internal ivy repositories.

This is an open source re-implementation of a previous [dependency search project](http://dev.bizo.com/2010/05/hackday-dependency-searching-using.html).

The original project itself was a hackday thing, and there have been a few things I've wanted to add/change that have been difficult with the current codebase.  I'm hoping the second time around will be better :).  That said, this is still very much a work-in-progress and also hastily hacked together :).


## Build & Run ##

```sh
$ sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.


## Credits


### Artwork
  Robot by Mike Hince from The Noun Project