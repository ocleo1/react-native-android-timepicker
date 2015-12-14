'use strict'

var React = require('react-native');
var { requireNativeComponent, PropTypes, StyleSheet, View, Text } = React;

var TimePickerClock = requireNativeComponent('ClockTimePickerAndroid', TimePickerAndroid);
var TimePickerSpinner = requireNativeComponent('SpinnerTimePickerAndroid', TimePickerAndroid);

var TimePickerAndroid = React.createClass({
  PropTypes: {
    ...View.propTypes,
    onTimeChange: PropTypes.func
  },

  getDefaultProps() {
    return {
      spinnerMode: true
    };
  },

  getInitialState() {
    return {
      hours: '00',
      minutes: '00'      
    };
  },

  _onTimeChange(event) {
    var hours = event.nativeEvent.hours;
    var minutes = event.nativeEvent.minutes;
    this.state.hours = hours < 10 ? '0' + hours : hours.toString();
    this.state.minutes = minutes < 10 ? '0' + minutes : minutes.toString();
    this.setState(this.state);
  },

  render() {
    var TimePicker = this.props.spinnerMode ? TimePickerSpinner : TimePickerClock;
    var timePickerStyle = this.props.spinnerMode ? styles.timePickerSpinner : styles.timePickerClock;

    return (
      <View style={styles.container}>
        <TimePicker style={timePickerStyle}
                    onTimeChange={this._onTimeChange}/>
        <Text style={styles.selectedTime}>
          {this.state.hours + ':' + this.state.minutes}
        </Text>
      </View>
    )
  }
});

var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF'
  },
  timePickerSpinner: {
    width: 300, 
    height: 300
  },
  timePickerClock: {
    width: 300, 
    height: 400
  },
  selectedTime: {
    textAlign: 'center',
    color: '#333333',
    marginTop: 5
  },
});

module.exports = TimePickerAndroid;
