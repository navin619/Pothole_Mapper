load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_18.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_24.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_22.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_25.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_30.txt
close all;
Z1 = Accelerometer_Data_18(:,3);
A = Accelerometer_Data_18(:,2);
Event = Accelerometer_Data_18(:,8);
Event1 = Accelerometer_Data_18(:,4);
n = numel(Z1);
C = 0:1:n-1;
 figure;
  plot(C,Z1);
Z = Z1;
n1 = numel(Z);
% figure;
% plot(A);
% figure;
% plot(A);
B = 0:1:n1-1;
%k = 0.5;
k= 0.3;
b = [1+k,-k-1];
a = [2,-2*k];
y = filter(b,a,Z);
% figure;
% plot(y);
h = find(Event == -1);
lfft=256; % FFT size
Zf=fft(Z,lfft);
%figure;plot((0:lfft-1),abs(Zf));
% High Pass filter for horizontal acceleration
l = 0.5;
bA = [1+l,-l-1];
aA = [2,-2*l];
yA = filter(bA,aA,A);
%plot(B,Z);
%h2 = figure;
figure;
plot(yA);
figure;
plot(A);




%  HorizontalFilter = A(39:41);
%  MatchHorizontal = HorizontalFilter(end:-1:1);
MatchHorizontalOut = filter(MatchHorizontal,1,A);

figure;
plot(MatchHorizontalOut);



























% X1 = y(356:362);
% X1slow1 = y(767:775);
% X1slow2 = y(780:784);
% D1 = yA(162:164);
Matchb = X1(end:-1:1);
% Matchbslow1 = X1slow1(end:-1:1);
% Matchbslow2 = X1slow2(end:-1:1);
% MatchHori = D1(end:-1:1);
MatchOut = filter(Matchb,1,y);
% MatchOutslow1 = filter(Matchbslow1,1,y);
% MatchOutslow2 = filter(Matchbslow2,1,y);
% figure;
% plot(MatchOutslow1);
% figure;
% plot(MatchOutslow2);
% figure;
% plot(MatchOut);
% figure;
% plot(MatchHoriOut);


%figure;
% Zs1 = Accelerometer_Data_8(:,3);
% As = Accelerometer_Data_8(:,2);
% Ap = Accelerometer_Data_10(:,2);
% ns = numel(Zs1);
% Cs = 0:1:ns-1;
% Zs = Zs1;
% ns1 = numel(Zs);
% 
% Bs = 0:1:ns1-1;
% ls = l;
% bs = [1+ls,-ls-1];
% as = [2,-2*ls];
% ys = filter(bs,as,As);
% yp = filter(bs,as,Ap);
% hs = find(As == -1);
% lffts=256; % FFT size
% Zfs=fft(Zs,lffts);
% [H,w] = freqz(b,a);
h
