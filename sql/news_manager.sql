
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);





CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `fullname` varchar(150) DEFAULT NULL,
  `status` int NOT NULL,
  `roleid` bigint NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role` (`roleid`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
);


CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `news` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `shortdescription` text,
  `content` mediumtext,
  `categoryid` bigint NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_news_category` (`categoryid`),
  CONSTRAINT `fk_news_category` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`)
);






CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `user_id` bigint NOT NULL,
  `new_id` bigint NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_user` (`user_id`),
  KEY `fk_comment_news` (`new_id`),
  CONSTRAINT `fk_comment_news` FOREIGN KEY (`new_id`) REFERENCES `news` (`id`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);








INSERT INTO `role` VALUES (1,'ADMIN','ADMIN',NULL,NULL,NULL,NULL),(2,'USER','USER',NULL,NULL,NULL,NULL);


INSERT INTO `user` VALUES (1,'admin','123456','admin',1,1,NULL,NULL,NULL,NULL),(15,'vutran','123456','Tran thanh tuan vu',1,2,'2020-09-07 10:16:42','2020-09-07 10:16:42','',''),(16,'vutran','123456','Tran thanh tuan vu',1,2,'2020-09-07 10:16:49','2020-09-07 10:16:49','',''),(17,'vutran','123456','Tran thanh tuan vu',1,2,'2020-09-07 10:22:15','2020-09-07 10:22:15','',''),(18,'vutran','123','Tran thanh tuan vu',1,2,'2020-09-07 10:23:26','2020-09-07 10:23:26','',''),(19,'vutran','123456','Tran thanh tuan vu',1,2,'2020-09-07 10:26:08','2020-09-07 10:26:08','','');


INSERT INTO `category` VALUES (1,'Thế Giới','the-gioi',NULL,NULL,NULL,NULL),(4,'Xã Hội','xa-hoi',NULL,NULL,NULL,NULL),(5,'Thể Thao','the-thao',NULL,NULL,NULL,NULL),(6,'Giáo Dục','giao-duc',NULL,NULL,NULL,NULL);


INSERT INTO `news` VALUES (21,'Ông Trump đề cử nữ thẩm phán quyền lực trước thềm bầu cử ','https://icdn.dantri.com.vn/thumb_w/640/2020/09/27/trump-1601172997103.jpg',' Tổng thống Mỹ Donald Trump chính thức thông báo đề cử bà Amy Coney Barrett làm thẩm phán Tòa án Tối cao bất chấp đảng Dân chủ tìm cách ngăn ông làm điều này trước cuộc bầu cử vào tháng 11.','Theo Reuters, tại cuộc họp báo ở Vườn Hồng, Nhà Trắng ngày 26/9, Tổng thống Mỹ Donald Trump đã thông báo đề cử bà Amy Coney Barrett là thẩm phán Tòa án Tối cao, thay thế cho nữ thẩm phán vừa qua đời Ruth Bader Ginsburg.\nNếu được Thượng viện phê chuẩn, bà Barrett sẽ trở thành phụ nữ thứ năm đảm nhận chức vụ trọn đời này ở Tòa án Tối cao Mỹ, và nâng thế đa số của số thẩm phán bảo thủ so với thẩm phán tự do lên 6 - 3. Bà Barrett là ứng viên thứ 3 do Tổng thống Trump chỉ định kể từ khi ông nhậm chức vào đầu năm 2017. Bà Barrett, 48 tuổi, là ứng viên thẩm phán Tòa án Tối cao trẻ tuổi nhất kể từ năm 1991',4,NULL,NULL,NULL,NULL),(22,'Hòa Bình sẽ kiểm tra việc xây dựng khẩu hiệu 11 chữ hết… hơn 10 tỷ đồng ','https://icdn.dantri.com.vn/thumb_w/640/2020/09/26/doi-ong-tuong-1601082180856.jpg',' Lãnh đạo tỉnh Hòa Bình đã chỉ đạo kiểm tra dự án xây dựng khẩu hiệu 11 chữ với số tiền hơn 10,2 tỷ đồng trên đồi Ông Tượng ở thành phố Hòa Bình.  Ngày 27/9, ông Nguyễn Tuấn Anh, Phó chánh Văn phòng UBND tỉnh Hòa Bình cho biết, sau khi các cơ quan báo chí có phản ánh về dự án xây dựng khẩu hiệu trên đồi Ông Tượng, lãnh đạo tỉnh đã có chỉ đạo kiểm tra lại dự án.','<p>Theo &ocirc;ng Anh, kế hoạch kiểm tra dự &aacute;n dự kiến c&oacute; trong ng&agrave;y thứ Hai (28/9) v&agrave; giao cho c&aacute;c Sở ng&agrave;nh kiểm tra, r&agrave; so&aacute;t tổng thể qu&aacute; tr&igrave;nh đầu tư x&acirc;y dựng của dự &aacute;n.</p>\n\n<p>Ghi nhận của <em>PV D&acirc;n tr&iacute;</em>, hiện c&ocirc;ng tr&igrave;nh x&acirc;y lắp khẩu hiệu 11 chữ tr&ecirc;n đồi &Ocirc;ng Tượng (phường Phương L&acirc;m, TP H&ograve;a B&igrave;nh) đang được C&ocirc;ng ty Cổ phần đầu tư v&agrave; ph&aacute;t triển Anh Kỳ triển khai.</p>\n\n<p>Đơn vị n&agrave;y mới lắp đặt xong được 3 chữ c&aacute;i. Những chữ n&agrave;y cao khoảng 10m, xung quanh c&ocirc;ng tr&igrave;nh được đ&oacute;ng nhiều cọc sắt, ph&iacute;a b&ecirc;n cạnh c&oacute; 2 chữ nữa đang được để sẵn chờ dựng.</p>\n\n<p>Trước đ&oacute;, như <em>D&acirc;n tr&iacute;</em> đ&atilde; phản &aacute;nh, Sở VH-TT&amp;DL l&agrave;m chủ đầu tư x&acirc;y dựng khẩu hiệu tr&ecirc;n đồi &Ocirc;ng Tượng c&oacute; 11 chữ với tổng kinh ph&iacute; hơn 10,2 tỷ đồng g&acirc;y x&ocirc;n xao dư luận.</p>\n\n<p>Người d&acirc;n cho rằng, kinh ph&iacute; bỏ ra x&acirc;y dựng khẩu hiệu với số tiền gần 1 tỷ đồng một chữ l&agrave; kh&ocirc;ng cần thiết v&igrave; H&ograve;a B&igrave;nh c&ograve;n l&agrave; tỉnh ngh&egrave;o, nhiều nơi điện đường, trường, trạm chưa được đầu tư x&acirc;y dựng khiến đời sống nh&acirc;n d&acirc;n c&ograve;n gặp nhiều kh&oacute; khăn.</p>\n\n<p>B&agrave; B&ugrave;i Thị Niềm, Gi&aacute;m đốc Sở VH-TT&amp;DL tỉnh H&ograve;a B&igrave;nh cho biết, việc x&acirc;y dựng khẩu hiệu tr&ecirc;n đồi &Ocirc;ng Tượng l&agrave; cần thiết v&agrave; hợp l&yacute;. Bởi hiện nay, khu vực n&agrave;y đang tập trung nhiều c&ocirc;ng tr&igrave;nh quan trọng bậc nhất của tỉnh như: tượng đ&agrave;i B&aacute;c Hồ, trụ sở Tỉnh ủy, UBND tỉnh, HĐND tỉnh v&agrave; ngay cạnh b&ecirc;n thủy điện H&ograve;a B&igrave;nh.</p>\n\n<p>Gi&aacute;m đốc Sở VH-TT&amp;DL H&ograve;a B&igrave;nh cho biết th&ecirc;m, việc x&acirc;y lắp khẩu hiệu 11 chữ tr&ecirc;n đồi &Ocirc;ng Tượng hết hơn 10 tỷ đồng l&agrave; do thi c&ocirc;ng tr&ecirc;n địa h&igrave;nh phức tạp, nhiều hạng mục phụ trợ hết nhiều kinh ph&iacute;.</p>\n\n<p>Theo đ&oacute;, hạng mục 11 chữ của khẩu hiệu sẽ được gia c&ocirc;ng giằng m&aacute;i th&eacute;p bằng th&eacute;p h&igrave;nh, gia c&ocirc;ng giằng th&eacute;p bằng th&eacute;p bản, lắp dựng giằng th&eacute;p li&ecirc;n kết, Bulong M16, tấm aluminium, đục lỗ tấm th&eacute;p bằng c&ocirc;ng nghệ CNC&hellip;.</p>\n\n<p>Để đảm bảo an to&agrave;n cho c&aacute;c c&ocirc;ng tr&igrave;nh xung quanh nơi thi c&ocirc;ng x&acirc;y lắp khẩu hiệu (tượng đ&agrave;i B&aacute;c Hồ tr&ecirc;n đỉnh đồi, c&aacute;c c&ocirc;ng tr&igrave;nh trụ sở v&agrave; nh&agrave; d&acirc;n ở ph&iacute;a dưới), đơn vị thi c&ocirc;ng đ&atilde; phải tiến h&agrave;nh việc khoan tạo lỗ neo để cắm neo gia cố m&aacute;i v&aacute;ch taluy, lắp dựng ống th&eacute;p, gia c&ocirc;ng giằng m&aacute;i th&eacute;p, bơm keo xi măng lỏng&hellip;</p>\n\n<p>Ngo&agrave;i ra, do vị tr&iacute; thi c&ocirc;ng tr&igrave;nh l&agrave; v&aacute;ch n&uacute;i cao n&ecirc;n việc vận chuyển c&aacute;c loại vật liệu như c&aacute;t, sỏi, đ&aacute; dăm, sắt th&eacute;p&hellip; đều phải l&agrave;m thủ c&ocirc;ng.</p>\n',4,'2020-09-27 09:51:27','2020-09-27 09:51:27','vutran',''),(23,'Hà Nội: Xe Range Rover bốc cháy ngùn ngụt trên cầu Chương Dương ','https://icdn.dantri.com.vn/thumb_w/640/2020/09/27/dfasdas-1601204264955.jpg','Chiếc Range Rover bốc cháy dữ dội trên cầu Chương Dương (TP.Hà Nội) khiến cảnh sát giao thông phải chặn cả 2 phía đầu cầu làn giữa để khống chế ngọn lửa, cẩu kéo chiếc xe khỏi hiện trường.','<p>Chiều ng&agrave;y 27/9, trao đổi với PV <em>D&acirc;n tr&iacute;</em>, chỉ huy Đội Cảnh s&aacute;t giao th&ocirc;ng (CSGT) số 1 (C&ocirc;ng an TP.H&agrave; Nội) cho biết, hiện Đội CSGT số 1 đang phối hợp với Đội CSGT số 5 tiến h&agrave;nh ph&acirc;n luồng, điều tiết giao th&ocirc;ng qua lại cầu Chương Dương v&agrave; cẩu k&eacute;o chiếc xe Range Rover đ&atilde; bị bốc ch&aacute;y tr&ecirc;n cầu ra khỏi hiện trường.</p>\n\n<p>https://icdn.dantri.com.vn/thumb_w/640/2020/09/27/dsfsdfs-1601204264935.jpg</p>\n\n<p>Theo đ&oacute;, sự việc xảy ra v&agrave;o khoảng 17h35 ng&agrave;y 27/9, tại khu vực cầu Chương Dương (phường Bồ Đề, quận Ho&agrave;n Kiếm, H&agrave; Nội). Chiếc Range Rover bốc ch&aacute;y tại l&agrave;n giữa, d&agrave;nh ri&ecirc;ng cho &ocirc;t&ocirc;. Ngọn lửa ch&aacute;y dữ dội bao tr&ugrave;m to&agrave;n bộ xe. Cột kh&oacute;i đen bốc l&ecirc;n cao h&agrave;ng chục m&eacute;t.</p>\n\n<p>&quot;Sau khi nhận được tin b&aacute;o, lực lượng CSGT đ&atilde; chặn đường cả 2 ph&iacute;a đầu cầu ở l&agrave;n &ocirc;t&ocirc;, người d&acirc;n hiện chỉ được di chuyển ở 2 l&agrave;n hỗn hợp b&ecirc;n ngo&agrave;i. Hiện ngọn lửa đ&atilde; được khống chế&quot;, vị l&atilde;nh đạo Đội CSGT số 1 th&ocirc;ng tin.</p>\n',1,'2020-09-27 14:22:35','2020-09-27 14:22:35','vutran',''),(24,'Bộ trưởng Nguyễn Chí Dũng: Việt Nam phải có tư duy vượt lên, không đi theo','https://icdn.dantri.com.vn/thumb_w/640/2020/09/29/bo-truong-dung-1601354760732.jpeg','Việt Nam nhất thiết cần có được tư duy đột phá, quyết tâm và táo bạo, dám nghĩ, dám làm; Phải có tư duy vượt lên trước chứ nhất quyết không chịu đi theo, đi sau\", Bộ trưởng Dũng nhấn mạnh.  Phát biểu tại Diễn đàn thường niên về \"Cải cách và Phát triển Việt Nam 2020 với chủ đề: Việt Nam - Hành động để phục hồi tăng trưởng nhanh theo hướng bền vững và bao trùm trong bối cảnh đại dịch Covid-19\"  diễn ra tại Hà Nội sáng nay (29/9), Bộ trưởng Bộ KH&ĐT Nguyễn Chí Dũng nhìn nhận Việt Nam, từ một quốc gia kém phát triển, bị ảnh hưởng nặng nề bởi chiến tranh, tỷ lệ đói nghèo cao, đã vươn lên mạnh mẽ và trở thành nước thu nhập trung bình (thấp) vào năm 2010.','<p>Quy m&ocirc; nền kinh tế hiện nay đ&atilde; tăng hơn 40 lần so với năm 1990. Thu nhập b&igrave;nh qu&acirc;n đầu người đ&atilde; tăng từ khoảng 100 USD trước năm 1990 l&ecirc;n gần 2.800 USD. Chất lượng cuộc sống của nh&acirc;n d&acirc;n ng&agrave;y c&agrave;ng được cải thiện r&otilde; rệt&quot;, Bộ trưởng Bộ Kế hoạch v&agrave; Đầu tư n&oacute;i.</p>\n\n<p>Tuy nhi&ecirc;n, theo Bộ trưởng, những th&agrave;nh tựu tr&ecirc;n đang bị đe dọa bởi th&aacute;ch thức lớn đến từ đại dịch Covid -19. Đại dịch đ&atilde; ảnh hưởng tới tất cả c&aacute;c ng&agrave;nh lĩnh vực, đặc biệt tới ng&agrave;nh dịch vụ, vận tải, du lịch, ăn uống, lưu tr&uacute;... Nhiều doanh nghiệp đ&atilde; buộc phải thu hẹp quy m&ocirc; sản xuất hoặc tạm ngừng hoạt động; h&agrave;ng loạt lao động bị mất, thiếu việc l&agrave;m, thu nhập bị giảm s&acirc;u, g&acirc;y kh&oacute; khăn cho việc bảo đảm an sinh, ổn định x&atilde; hội.</p>\n\n<p>B&ecirc;n cạnh đ&oacute;, Việt Nam vẫn đang phải đối mặt với những hạn chế, yếu k&eacute;m nội tại của một nền kinh tế đang ph&aacute;t triển với mức thu nhập trung b&igrave;nh thấp, tăng trưởng phụ thuộc nhiều v&agrave;o vốn, lao động gi&aacute; rẻ v&agrave; khu vực đầu tư nước ngo&agrave;i. Do đ&oacute;, trong trung v&agrave; d&agrave;i hạn, vượt qua bẫy thu nhập trung b&igrave;nh, thu hẹp khoảng c&aacute;ch ph&aacute;t triển với c&aacute;c quốc gia kh&aacute;c, giải quyết c&aacute;c th&aacute;ch thức m&ocirc;i trường, x&acirc;y dựng nền kinh tế độc lập, tự chủ... l&agrave; những nhiệm vụ lớn đặt ra cho Việt Nam.</p>\n\n<p>Trong ngắn hạn, Việt Nam cần nhanh ch&oacute;ng phục hồi tăng trưởng kinh tế, bảo đảm an sinh x&atilde; hội, tận dụng hiệu quả c&aacute;c cơ hội ph&aacute;t triển xuất hiện từ khi xảy ra đại dịch Covid -19.</p>\n\n<p>Để ứng ph&oacute; với những kh&oacute; khăn, th&aacute;ch thức tr&ecirc;n, Bộ trưởng Bộ KH&amp;ĐT cho rằng, Việt Nam cần tận dụng tối đa những lợi thế, nhận diện r&otilde; r&agrave;ng c&aacute;c cơ hội đang c&oacute;.</p>\n\n<p>Từ g&oacute;c độ của Việt Nam cũng như đ&aacute;nh gi&aacute; của giới ph&acirc;n t&iacute;ch quốc tế, Bộ KH&amp;ĐT nhận thấy, Việt Nam đang ở thời điểm quan trọng để chuyển m&igrave;nh ph&aacute;t triển nhanh v&agrave; bền vững.</p>\n\n<p>&quot;Tiềm năng đất nước, bối cảnh ph&aacute;t triển mới, đặc biệt l&agrave; bối cảnh &ldquo;hậu Covid-19&rdquo; v&agrave; cuộc C&aacute;ch mạng c&ocirc;ng nghiệp lần thứ 4 (CMCN 4.0) đang tạo ra cơ hội thuận lợi để t&aacute;i cơ cấu, chuyển đổi số, chuyển đổi m&ocirc; h&igrave;nh hướng tới tăng trưởng nhanh, bền vững v&agrave; bao tr&ugrave;m&quot;, người đứng đầu ng&agrave;nh Kế hoạch v&agrave; Đầu tư nh&igrave;n nhận.</p>\n\n<p>Theo Bộ trưởng Dũng, với việc tham gia nhiều hiệp định thương mại tự do thế hệ mới như EVFTA v&agrave; CPTPP, Việt Nam đang c&oacute; cơ hội lớn để hội nhập, tham gia s&acirc;u hơn v&agrave;o mạng sản xuất thế giới, lựa chọn c&aacute;c dự &aacute;n FDI c&oacute; chất lượng để tiến l&ecirc;n c&aacute;c nấc thang cao hơn trong c&aacute;c chuỗi gi&aacute; trị to&agrave;n cầu.</p>\n\n<p>&quot;Cơ hội t&aacute;i cơ cấu nền kinh tế, chuyển đổi số cũng đang rộng mở, đặc biệt khi kinh tế kh&ocirc;ng tiếp x&uacute;c, thương mại điện tử của Việt Nam đ&atilde; tạo được những bước ph&aacute;t triển nhanh trong đại dịch&quot;, Bộ trưởng KH&amp;ĐT n&oacute;i.</p>\n\n<p>Bộ trưởng Dũng nhấn mạnh: Việt Nam phải chủ động đưa ra đường hướng chiến lược, c&aacute;c quyết s&aacute;ch cho tương lai ph&aacute;t triển của đất nước. Đặc biệt, để khắc phục tồn tại, kh&oacute; khăn v&agrave; tận dụng được những tiềm năng v&agrave; cơ hội đề cập ở tr&ecirc;n, ch&uacute;ng ta nhất thiết cần c&oacute; được tư duy đột ph&aacute;, quyết t&acirc;m v&agrave; t&aacute;o bạo, d&aacute;m nghĩ, d&aacute;m l&agrave;m; phải c&oacute; tư duy vượt l&ecirc;n trước chứ nhất quyết kh&ocirc;ng chịu đi theo, đi sau.</p>\n\n<p>Chỉ khi đ&oacute;, ch&uacute;ng ta mới c&oacute; thể nắm chặt lấy c&aacute;c cơ hội, bắt kịp, tiến c&ugrave;ng sự ph&aacute;t triển của thế giới. Ngược lại, nếu kh&ocirc;ng nhanh ch&oacute;ng tận dụng thời cơ v&agrave; đổi mới tư duy, th&igrave; nguy cơ tụt hậu, khoảng c&aacute;ch ph&aacute;t triển của Việt Nam với c&aacute;c quốc gia sẽ ng&agrave;y c&agrave;ng lớn hơn&quot;, &ocirc;ng Dũng nhấn mạnh.</p>\n',4,'2020-09-29 07:43:27','2020-09-29 07:43:27','vutran','');


INSERT INTO `comment` VALUES (2,'noi dung 1',15,21,NULL,NULL,NULL,NULL),(3,'noi dung 2',15,21,NULL,NULL,NULL,NULL),(4,'noi dung 3',15,22,NULL,NULL,NULL,NULL);
