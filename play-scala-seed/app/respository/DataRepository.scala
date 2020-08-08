package respository

import models.{Comment, Post}

class DataRepository {
  val posts = Seq(
    Post(1, "This is first blog first"),
    Post(2, "This is second blog first")
  )

  val comments = Seq(
    Comment(1, 1, "This is first Comment", "Author Name"),
    Comment(2, 1, "This is first Comment", "Author Name"),
    Comment(3, 2, "This is first Comment", "Author Name")
  )

  def getPost(postId: Int): Option[Post] = posts.collectFirst {
    case p if p.id == postId => p
  }

  def getComments(postId: Int): Seq[Comment] = comments.collect {
    case c if c.postId == postId => c
  }
}
