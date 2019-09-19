import { createAppContainer } from "react-navigation";
import { createStackNavigator } from "react-navigation-stack";

import QuoteScreen from "./components/QuoteScreen";

const MainNavigator = createStackNavigator({
  Quote: { screen: QuoteScreen }
});

const AppContainer = createAppContainer(MainNavigator);

export default AppContainer;
