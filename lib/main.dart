import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ChangeNotifierProvider(
      create: (context) => CountModel(),
      child: MaterialApp(
        home: MyHomePage(),
      ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return MyHomePageState();
  }
}

class MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(),
      body: Column(
        children: <Widget>[
          Expanded(
            child: Center(
              child: MyContent(),
            ),
          ),
        ],
      ),
    );
  }
}

class MyContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Consumer<CountModel>(builder: (context, myCount, child) {
      return Column(
        children: <Widget>[
          Text(
            '${myCount.value}',
            style: TextStyle(fontSize: 30),
          ),
          myRaiseButton()
        ],
      );
    });
  }
}

class myRaiseButton extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    final myCount = Provider.of<CountModel>(context);

    // TODO: implement build
    return RaisedButton(
      onPressed: (){
        myCount.value++;
        print(myCount.value);
      },
      child: Icon(Icons.add_circle),
    );
  }
}

class CountModel with ChangeNotifier {
  int _countt = 0;

  int get value => _countt;

  set value(int value) {
    _countt = value;
    notifyListeners();
  }
}