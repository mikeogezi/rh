"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const react_navigation_1 = require("react-navigation");
const react_navigation_stack_1 = require("react-navigation-stack");
const QuoteScreen_1 = __importDefault(require("./components/QuoteScreen"));
const MainNavigator = react_navigation_stack_1.createStackNavigator({
    Quote: { screen: QuoteScreen_1.default }
});
const AppContainer = react_navigation_1.createAppContainer(MainNavigator);
exports.default = AppContainer;
//# sourceMappingURL=AppContainer.js.map