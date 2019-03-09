package com.github.tkawachi

import play.api.mvc.{ RequestHeader, EssentialAction, EssentialFilter }

/**
 * Wrapped EssentialFilter which filters only predicate returns true.
 * <p />
 * For example, to create a CSRFFilter which is only applied to paths starting with
 * "/foo".
 *
 * {{{
 *   import com.github.tkawachi.PlayFilterOnly._
 *
 *   CSRFFilter().only(_.path.startsWith("/foo"))
 * }}}
 */
case class PlayFilterOnly(underlying: EssentialFilter, predicate: RequestHeader => Boolean)
  extends EssentialFilter {

  /**
   * Apply underlying filter only when predicate returns true.
   */
  override def apply(next: EssentialAction): EssentialAction = new EssentialAction {
    private[this] val filteredNext = underlying(next)
    override def apply(rh: RequestHeader) = if (predicate(rh)) filteredNext(rh) else next(rh)
  }
}

object PlayFilterOnly {

  implicit class EssentialFilterFilterOnlyImplicit(val filter: EssentialFilter)
    extends AnyVal {
    def only(predicate: RequestHeader => Boolean) = new PlayFilterOnly(filter, predicate)
  }
}
