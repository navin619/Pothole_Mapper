load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_16.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_10.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_8.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_23.txt
k = 0.3;

Zs1 = Accelerometer_Data_16(:,3);
As = Accelerometer_Data_16(:,4);
%Zs1 = Zs1(1:30);
% Zs1 = Accelerometer_Data_10(:,3);
% As = Accelerometer_Data_10(:,4);
ns = numel(Zs1);
nas = numel(As);
Cs = 0:1:ns-1;
% hs1 = figure;
% plot(Cs,Zs1);
%Zs = Zs1(619:846);
Zs = Zs1;
ns1 = numel(Zs);

Bs = 0:1:ns1-1;
ks = k;
lowbs = [1, -1];
bs = [1+ks,-ks-1];
as = [2,-2*ks];
ys = filter(bs,as,Zs);
%plot(Bs,Zs);
% h2s = figure;
% plot(ys);
hs = find(As == -1);
lffts=256; % FFT size
Zfs=fft(Zs,lffts);
%figure;plot((0:lffts-1),abs(Zfs));



%figure;
[H,w] = freqz(bs,as);
%plot(w/pi,abs(H));








Z1 = Accelerometer_Data_16(:,3);
A = Accelerometer_Data_16(:,4);
%Z1 = Z1;
n = numel(Z1);
C = 0:1:n-1;
% figure;
% plot(C,Z1);
%Z = Z1(303:440);
Z = Z1;
n1 = numel(Z);

B = 0:1:n1-1;
k = 0.3;
lowb = [1, -1];
b = [1+k,-k-1];
a = [2,-2*k];
y = filter(b,a,Z);
%plot(B,Z);
%h2 = figure;

h = find(A == -1);
lfft=256; % FFT size
Zf=fft(Z,lfft);
%figure;plot((0:lfft-1),abs(Zf));



X1 = y(356:362);
ys = ys;
%  figure;
%  plot(y);
Matchb = X1(end:-1:1);
MatchOut = filter(Matchb,1,ys);
figure;
plot(MatchOut);






% 
% 
% figure;
% 
% Sample = [5, 4,3,2,1,5,-5,5,5,4,3,2,1, 5,5,5];
% plot(Sample);
% s = Sample(6:7);
% matchsampleb = s(end:-1:1);
% matchout = filter(matchsampleb,1,Sample);
% figure;
% plot(matchout);
hs