## Locker
Given: Small Locker有剩余容量, 包裹尺寸为S的包
When：存包
Then：存包成功，返回一张Small型号的票

Given：Small Locker已满，包裹尺寸为S的包
When：存包
Then：存包失败，提示已满

Given：Small Locker，一张Small型号票
When：取包
Then：返回票对应的包

Given：Small Locker，一张无效的Small型号的票
When：取包
Then：取包失败，提示票无效

Given：Small Locker，不是small型号的票
When：取包
Then：取包失败，提示票的型号不对

## PrimaryLockerRobot
Given：PrimaryLockerRobot管理2个medium的Locker，第一个有剩余容量，第二个有剩余容量，包裹尺寸为M的包
When：存包
Then：存入第一个locker返回一张medium型号的票
 
Given：PrimaryLockerRobot管理2个medium的Locker，第一个有剩余容量，第二个没有剩余容量，包裹尺寸为M的包
When：存包
Then：存入第一个locker返回一张medium型号的票

Given：PrimaryLockerRobot管理2个medium的Locker，第一个没有剩余容量，第二个有剩余容量，包裹尺寸为M的包
When：存包
Then：存包第二个locker，返回一张medium型号的票

Given：PrimaryLockerRobot管理2个medium的Locker，第一个没有剩余容量，第二个也没有剩余容量，包裹尺寸为M的包
When：存包
Then：存包失败，提示已满

Given：PrimaryLockerRobot管理1个medium的Locker，一张medium型号的票
When：取包
Then：返回票对应的包

Given：PrimaryLockerRobot管理1个medium的Locker，一张无效的medium型号的票
When：取包
Then：取包失败，提示票无效

Given：PrimaryLockerRobot管理1个medium的Locker，不是medium型号的票
When：取包
Then：取包失败，提示票的型号不对

## SuperLockerRobot
Given：SuperLockerRobot管理1个large的Locker且有剩余空间，包裹尺寸为L的包
When：存包
Then：存入成功，返回一张large型号的票

Given：SuperLockerRobot管理2个large的Locker，第一个总容量为10，剩余容量为5，第二个总容量为5，剩余容量为3，包裹尺寸为L的包
When：存包
Then：存入第2个locker，返回一张large型号的票

Given：SuperLockerRobot管理2个large的Locker，第一个总容量为10，剩余容量为5，第二个总容量为8，剩余容量为4，包裹尺寸为L的包
When：存包
Then：存入第1个locker，返回一张large型号的票

Given：SuperLockerRobot管理2个large的Locker，第一个没有剩余容量，第二个也没有剩余容量，包裹尺寸为L的包
When：存包
Then：存包失败，提示已满

Given：SuperLockerRobot管理1个large的Locker，一张large型号的票
When：取包
Then：返回票对应的包

Given：SuperLockerRobot管理1个large的Locker，一张无效的large型号的票
When：取包
Then：取包失败，提示票无效

Given：SuperLockerRobot管理1个large的Locker，不是large型号的票
When：取包
Then：取包失败，提示票的型号不对

## LockerRobotManager
Given：LockerRobotManager管理一个small的Locker且有剩余容量、两个robot，第一个是PrimaryLockerRobot（管理一个Locker）有剩余容量，第二个是SuperLockerRobot（管理一个Locker）也有剩余容量，包裹尺寸为S的包
When：存包
Then：存入small类型的locker中，返回一张small型号的票

Given：LockerRobotManager管理一个small的Locker且没有剩余容量、两个robot，第一个是PrimaryLockerRobot（管理一个Locker）有剩余容量，第二个是SuperLockerRobot（管理一个Locker）也有剩余容量，包裹尺寸为S的包
When：存包
Then：存包失败，提示已满

Given：LockerRobotManager管理一个small的Locker且有剩余容量、两个robot，第一个是PrimaryLockerRobot（管理一个Locker）有剩余容量，第二个是SuperLockerRobot（管理一个Locker）有剩余容量，包裹尺寸为M的包
When：存包
Then：存入PrimaryLockerRobot中，返回一张medium型号的票

Given：LockerRobotManager管理一个small的Locker且有剩余容量、两个robot，第一个是PrimaryLockerRobot（管理一个Locker）没有剩余容量，第二个是SuperLockerRobot（管理一个Locker）有剩余容量，包裹尺寸为M的包
When：存包
Then：存包失败，提示已满

Given：LockerRobotManager管理一个small的Locker且有剩余容量、两个robot，第一个是PrimaryLockerRobot（管理一个Locker）有剩余容量，第二个是SuperLockerRobot（管理一个Locker）也有剩余容量，包裹尺寸为L的包
When：存包
Then：存入small的locker中，返回一张large型号的票

Given：LockerRobotManager管理一个small的Locker且有剩余容量、两个robot，第一个是PrimaryLockerRobot（管理一个Locker）有剩余容量，第二个是SuperLockerRobot（管理一个Locker）没有剩余容量，包裹尺寸为L的包
When：存包
Then：存包失败，提示已满

Given：LockerRobotManager管理一个small的Locker、两个robot，第一个是PrimaryLockerRobot（管理一个Locker），第二个是SuperLockerRobot（管理一个Locker），small型号的票
When：取包
Then：返回票对应的包

Given：LockerRobotManager管理一个small的Locker、两个robot，第一个是PrimaryLockerRobot（管理一个Locker），第二个是SuperLockerRobot（管理一个Locker），medium型号的票
When：取包
Then：返回票对应的包

Given：LockerRobotManager管理一个small的Locker、两个robot，第一个是PrimaryLockerRobot（管理一个Locker），第二个是SuperLockerRobot（管理一个Locker），medium型号的票
When：取包
Then：返回票对应的包

Given：LockerRobotManager管理一个small的Locker、两个robot，第一个是PrimaryLockerRobot（管理一个Locker），第二个是SuperLockerRobot（管理一个Locker），无效票
When：取包
Then：取包失败，提示票无效
