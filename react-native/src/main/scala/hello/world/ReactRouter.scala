package hello.world

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.native.{Text, View}

import typings.reactRouterNative.components._
import typings.reactRouter.mod._

@react object ReactRouter {

  case class Props(`match`: `match`[_])

  val component = FunctionalComponent[Props] {
    case Props(m) =>
      def link(title: String, path: String): ReactElement =
        Link(to = m.url + path, style = Styles.subNavItemStyle)(Text(style = Styles.topicStyle)(title))

      View(
        Text(style = Styles.title)("React Router demo": ReactElement),
        Text(style = Styles.headerStyle)("Topics"),
        View(
          link("Rendering with React", "/rendering"),
          link("Components", "/components"),
          link("Props v. State", "/props-v-state")
        ),
        Route(
          RouteProps(
            path = m.path + "/:topicId",
            render = props => Topic(props.`match`.asInstanceOf[`match`[Topic.Param]])
          )
        ),
        Route(RouteProps(path = m.path, render = _ => Text("Please select a topic"), exact = true))
      )
  }
}
