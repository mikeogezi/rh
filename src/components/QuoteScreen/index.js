import React from "react";
import {
  View,
  StyleSheet,
  Text,
  TouchableHighlight
} from "react-native";

import LinearGradient from "react-native-linear-gradient";
import gradients from "../../../data/gradients";
import _ from "lodash";

export default class QuoteScreen extends React.Component {
  static navigationOptions = {
    title: 'BeInspired',
  };

  constructor (props) {
    super(props);
    this.state = {
      gradient: _.sample(gradients)
    };
  }

  changeGradient = () => {
    this.setState({ gradient: _.sample(gradients)});
  }

  render() {
    const { colors } = this.state.gradient;

    return (
        <LinearGradient colors={colors} style={styles.linearGradient}>
          <TouchableHighlight style={styles.touchableContainer} underlayColor={'transparent'} activeOpacity={0} onPress={this.changeGradient}>
            <View style={styles.container}>
              <Text style={styles.quoteText}>You are a star.</Text>
            </View>
          </TouchableHighlight>
        </LinearGradient>
    );
  }
}

const styles = StyleSheet.create({
  touchableContainer: {
    width: '100%',
    height: '100%',
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  quoteText: {
    fontSize: 32,
    fontWeight: 'bold',
    color: 'white'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 60,
  },
  instructions: {
    padding: 10,
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  linearGradient: {
    flex: 1,
    paddingLeft: 0,
    paddingRight: 0,
    borderRadius: 0
  },
  buttonText: {
    fontSize: 30,
    fontFamily: 'Gill Sans',
    textAlign: 'center',
    margin: 10,
    color: '#ffffff',
    backgroundColor: 'transparent',
  }
});

