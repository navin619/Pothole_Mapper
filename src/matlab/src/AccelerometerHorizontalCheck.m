load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_25.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_19.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_10.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_26.txt
load /media/Drive/Documents/Master_Documents/Courses/Software' Engineering Concepts - 573'/Project/SubVersion/c2d/Accelerometer_Data/Camry_April_5/Accelerometer_Data_30.txt

Z1 = Accelerometer_Data_25(:,3);
A = Accelerometer_Data_25(:,2);
Event = Accelerometer_Data_25(:,8);
%Z1 = Z1;
n = numel(Z1);
C = 0:1:n-1;
figure;
plot(C,Z1);
%Z = Z1(303:440);
Z = Z1;
n1 = numel(Z);
figure;
plot(A);
B = 0:1:n1-1;
k = 0.3;
lowb = [1, -1];
b = [1+k,-k-1];
a = [2,-2*k];
y = filter(b,a,Z);
%plot(B,Z);
%h2 = figure;
%figure;
%plot(y);
h = find(Event == -1);
lfft=256; % FFT size
Zf=fft(Z,lfft);
%figure;plot((0:lfft-1),abs(Zf));
% High Pass filter for horizontal acceleration
l = -0.3;
bA = [1+l,-l-1];
aA = [2,-2*l];
yA = filter(bA,aA,A);
%plot(B,Z);
%h2 = figure;
figure;
plot(yA);


%figure;
Zs1 = Accelerometer_Data_8(:,3);
As = Accelerometer_Data_8(:,2);
Ap = Accelerometer_Data_10(:,2);
Asb26 = Accelerometer_Data_26(:,2);
Asb30 = Accelerometer_Data_30(:,2);
%Zs1 = Zs1;
ns = numel(Zs1);
Cs = 0:1:ns-1;
%hs1 = figure;
%plot(Cs,As);
%Zs = Zs1(619:846);
Zs = Zs1;
ns1 = numel(Zs);

Bs = 0:1:ns1-1;
ls = l;
lowbs = [1, -1];
bs = [1+ls,-ls-1];
as = [2,-2*ls];
ys = filter(bs,as,As);
yp = filter(bs,as,Ap);
ysb26 = filter(bs,as,Asb26);
ysb30 = filter(bs,as,Asb30);
%plot(Bs,Zs);
% h2s = figure;
% plot(ys);
hs = find(As == -1);
lffts=256; % FFT size
Zfs=fft(Zs,lffts);
%figure;plot((0:lffts-1),abs(Zfs));



%figure;
[H,w] = freqz(b,a);
%plot(w/pi,abs(H));

% figure;
% plot(Asb26);
% figure;
% plot(Asb30);

% h2s = figure;
% plot(yp);
% h2s = figure;
% plot(ysb26);
% h2s = figure;
% plot(ysb30);
h