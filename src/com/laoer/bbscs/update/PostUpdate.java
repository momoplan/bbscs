package com.laoer.bbscs.update;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.bean.ForumMain;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.ForumConfig;

public class PostUpdate {

	private ForumService forumService;

	private ForumService forumHistoryService;

	private BoardService boardService;

	private UserService userService;

	private ForumConfig forumConfig;

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ForumConfig getForumConfig() {
		return forumConfig;
	}

	public void setForumConfig(ForumConfig forumConfig) {
		this.forumConfig = forumConfig;
	}

	public ForumService getForumHistoryService() {
		return forumHistoryService;
	}

	public void setForumHistoryService(ForumService forumHistoryService) {
		this.forumHistoryService = forumHistoryService;
	}

	public void update() {
		String safepath = this.getForumConfig().getSafePath();
		if (!safepath.endsWith("/")) {
			safepath = safepath + "/";
		}
		File updateFile = new File(safepath + "update.txt");
		int flag = 0;
		try {
			String updeted = FileUtils.readFileToString(updateFile, Constant.CHARSET);
			if (StringUtils.isNotBlank(updeted)) {
				updeted = updeted.trim();
				flag = Integer.parseInt(updeted);
			}
		} catch (IOException e) {
			flag = 0;
		}
		if (flag == 0) {
			List l = this.getForumService().findForums(-1, -1, -1, -1, null);
			this.changeFile(l);
			// this.changeFilePath(l);

			l = this.getForumHistoryService().findForums(-1, -1, -1, -1, null);
			this.changeFile(l);
			// this.changeFilePath(l);
			try {
				FileUtils.writeStringToFile(updateFile, "1", Constant.CHARSET);
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}

	private void changeFile(List l) {
		for (int i = 0; i < l.size(); i++) {
			Forum f = (Forum) l.get(i);
			if (f.getIsVote() == 0) {
				System.out.println(i + ":Post id:" + f.getId());
				String detail = f.getDetail();

				String postFileName = "P_" + f.getBoardID() + "_" + f.getId() + ".txt";
				File postFile = new File(this.getForumConfig().getForumPath(f.getBoardID(), f.getPostTime())
						+ postFileName);
				try {
					FileUtils.writeStringToFile(postFile, detail, Constant.CHARSET);
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				f.setDetail(postFileName);

				try {
					this.getForumService().saveOrUpdateForum(f);
				} catch (BbscsException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
		}
	}

	/*
	private void changeFilePath(List l) {
		for (int i = 0; i < l.size(); i++) {
			Forum f = (Forum) l.get(i);

			String detail = "";
			File oldFile = new File(this.getForumConfig().getForumPathOld(f.getBoardID(), f.getPostTime())
					+ f.getDetail());
			if (oldFile.exists()) {
				System.out.println(i + ":Post id:" + f.getId());
				try {
					// detail = FileUtils.readFileToString(oldFile);
					detail = FileUtils.readFileToString(oldFile, Constant.CHARSET);
				} catch (IOException e1) {
					// TODO 自动生成 catch 块
					e1.printStackTrace();
				}

				String postFileName = f.getDetail();
				File postFile = new File(this.getForumConfig().getForumPath(f.getBoardID(), f.getPostTime())
						+ postFileName);
				try {
					FileUtils.writeStringToFile(postFile, detail, Constant.CHARSET);
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}

				try {
					FileUtils.forceDelete(oldFile);
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			oldFile = null;
		}
	}*/

	public void insert() {
		Board b = this.getBoardService().getBoardByID(3);
		UserInfo ui = this.getUserService().findUserInfoById("402880e61117a627011117a6c9a70001");
		for (int i = 0; i < 3000; i++) {
			System.out.println(i);
			long nowtime = System.currentTimeMillis(); //
			String detail = "自从某专吃清史饭的大作家将作品改编成电视剧以来，清朝入关以来的第三个皇帝世宗胤，即雍正皇帝的知名度陡然上升。北京胡同里的老太太并她们手里牵着的小孙子，都知道咱大清国有一个雍正皇帝。鄙人生性疏散，向来耐不住电视剧的冗长加唠叨，所以尽管《雍正王朝》几番热播，我却始终没有看过。不过，虽然眼睛没看电视，却依然逃不脱雍正的阴影，因为总是有人来问，雍正到底是个什么东西，是不是像电视剧里说的那样好。说实在的，雍正是个什么东西，我现在也说不好。此公在历史上的名声一直不太好，又偏偏夹在两个名声过大也过好的皇帝之间，想不灰头土脸都难。虽然某作家给他平了一回反，也未必真的能翻过来。此公没有他的老爹康熙那样兴趣广泛，也没有他儿子乾隆那样诗兴泉涌，只有一笔字据说还说得过去（我见过的，无疑比到处题字的乾隆强多了），当政时间不长，又没有多少可说的事情。不过，在我看来，跟其他的清朝皇帝比起来，这个人多少有点怪，让后人面对他的时候总忍不住想说点什么，却往往说不出什么来。正是这个雍正，登基做皇帝，空着正殿乾清宫不住，非要搬到偏殿养心殿忍着，弄得皇宫的政治地理大乱，大家都找不着北。雍正在位的时候，组成了一个机要的秘书班子——军机处，在他以后，军机处取代内阁成为国家的核心决策机关。但是，雍正有秘书却不爱用，总是自己亲自批奏折，往往批得很长，口吻就像个爱唠叨的老太太。不管臣子功劳有多大，让他抓住点小毛病就嗦个没完，非让你灵魂深处爆发革命，将自己批倒批臭而后止。批奏折批得长，不见得天天都那么忙，至少不像周公似的，吃顿饭都要吐出来好几次。所以，雍正也有闲功夫看看戏。看戏可是看戏，别的皇帝看过也就罢了，顶多当时一喜或者一悲，高兴了赏几两银子给扮戏的太监，不高兴了赏他们一顿板子。可是人家雍正不是这样，看戏都能看出一段轶事来。说是一次他看《绣襦记·打子》，此剧是明人根据唐代传奇《李娃传》改编的，说的是名门公子郑元和名妓李亚仙的爱情故事。《打子》一折演的是担任常州刺史的郑父，看到儿子因迷恋娼家最后流落街头，靠为人唱挽歌度日，一怒之下痛打儿子的情节。这段戏让雍正十分高兴，尤其喜欢扮演郑父的小太监（大概更多地是喜欢这种贾政似的人物），于是把他叫到身边赏饭。在吃饭的时候，小太监一时忘情，顺口问了一句，现在的常州刺史是谁？雍正陡然翻转脸皮，勃然大怒，说你这优伶贱辈，怎么敢问国家的名器？当场下令将小太监杖毙廊下。雍正不独性格乖戾，行事还有点天真。从来历史上轮到争位的时候，父子反目、兄弟相残都是免不了的事。胜利者对付政敌，或杀或坑都是应有之意，别人其实也说不出什么更多的来。君不见，李世民杀了两个兄弟，逼他父亲让了位，最后还不是得了明君之名。可是，雍正对付他的两个争位的兄弟，也不杀也不坑，却封他们为“阿其那”（猪）、“塞思黑”（狗）。殊不知，这样的封法细究起来却大有不妥，自家兄弟是猪狗，那他自己呢？他的父亲呢？这还不算雍正行事中最天真的，雍正一生最自以为是的糗事，要算对曾静案的处理。雍正六年（1728年），湖南出了个反清案件，事主名叫曾静，是个屡试不第的儒生，因受到明朝遗臣吕留良诗文的影响，锐意反清。一日，不知从哪里听说现任川陕总督岳钟琪是岳飞的后代，于是让他的弟子张熙前去投书，劝说岳反清。结果不问可知，即使岳钟琪跟曾静一样有华夷情结，也断然不会为了一个岳武穆的遥远虚名而甘冒身家性命之险。于是，这个送上门去的“反革命小集团”被连窝端掉，圣眷正隆的岳钟琪以诱捕曾静洗清了自己。无论在哪个朝代，出几个谋反案件都不稀奇，更何况满清以异族入主中原，虽然过了百十年，乡下的迂儒硬是坚持“民族大义”和华夷之辨，那也是没有办法的事情。不过这次情况大有不同，在查抄出来的“反革命文件”中，居然有大量宣传雍正争夺皇位的内容，说他如何谋父、逼母、弑兄、屠弟，以及贪财、好杀、淫色，等等，几乎跟当年的隋炀帝杨广差不多。这样一来，曾静案就不再是一般反对异族统治的逆案了，而是主要针对雍正个人的谋反行为，这样的逆案无疑更容易引起龙颜大怒。曾静等人被逮到京后，实际上是雍正亲自操纵案件的审理，即使到了今天，我们依然可以从当时的上谕中，窥见雍正的恨恨连声之态。按照传统时代的常理，对于这样一个策动大臣谋反，并对现任皇帝进行恶毒攻击的“反革命小集团”的成员，凌迟处死并夷之九族本是应有之意，只有这样，才可稍解皇帝和拍马屁的臣子们之气于万一。可是，雍正对曾静案的处理，却出乎所有人的意料。雍正下令将审讯曾静的记录整理成册，并在前面加上了长长的按语（上谕），起名为《大义觉迷录》。只是这个审讯记录过于整齐，明显透着点“做”的意思。尽管雍正对曾静等人的“谣言”十分恼怒，认为自己连做梦都想不到，属于犬吠狼嚎，本不足以理会，但在上谕中还是花了很大的篇幅，论证自己对父母如何地好，如何地孝顺，对兄弟如何地仁至义尽，总之是将曾静等人私下散布的所有对他不利的言语，一一详加驳斥。而且“审讯记录”更是采用一问一答的方式，先由审官按雍正的旨意质问，再由曾静作答，让曾静在稍做一点解释之后，将自己骂得狗血淋头，从而反证他散布的有关雍正争位的种种言语是如何地荒诞不经。《大义觉迷录》印行之后，发往各个府州县，每个学宫都备一册，成为学子们的必读书。与此同时，雍正还下令在曾静的家乡湖南成立观风俗使衙门，将曾静、张熙释放，派到观风俗使衙门效力，曾静倒也是个可人，十分配合，不仅自愿到各地宣讲雍正皇帝的“圣德”，而且还写了一篇《归仁说》，表达自己诚心忏悔之意。雍正这么做的意图，事后看来应该是很明白的。他不是不恨曾静这些人，更不是心存仁慈，企图感化顽愚（像某作家说的那样）。因为这个案件涉及到那么多攻击他私德的谣言，他感到委屈，需要有个辩白的机会，否则心中的恶气无论如何也出不来，所以就颇费心思地设计了这样一种处理方式。《大义觉迷录》就是一种特殊形式的辩驳，一种最后将对方彻底而且无条件驳倒的辩驳。让曾静等人自己下去痛骂自己，现身说法，对皇帝的清誉而言，显然比杀了他们要有利得多。然而，自以为聪明而且急于刷洗自己的雍正，却忽视了一个很关键的问题，传统政治是黑幕政治，或者说是黑箱政治，上层的事情，既无必要，也无可能昭示于公众。尽管小道消息可以传得满天飞，但一般不允许有关部门出来解释澄清；时间长了，自然大家对所有的事都糊里糊涂，将信将疑。这种状况，在多数情况下反而有利于政治的操控。雍正为了把自己刷洗干净，将最隐秘的宫廷斗争抖落出来，昭示天下，甚至不知道分个保密等级，结果自然是越抹越黑，许多原来不知道这些谣言的地方，反而都知道了。那修整得过于整齐的“辩驳书”，实际上未必有雍正想像的那样具有说服力，说不定副作用更大。因为雍正没有也不可能改变政治黑幕化的传统，人们还是按照以往的惯例来分析判断事物，正事反看，反事正看，沿着字里行间，寻找微言大义，捕风捉影，发挥想像。事情的结果我们现在都知道了，在清朝诸帝中，关于雍正的传言和非议是最多的。雍正的儿子乾隆是个聪明人，他上台之后，马上下令将曾静、张熙等人处死，收回所有散在地方的《大义觉迷录》，加以销毁，任何人不得收存，否则严加惩处。如此说来，雍正作为皇帝，倒是有几分天真之处，只是这种天真并不可爱。自从某专吃清史饭的大作家将作品改编成电视剧以来，清朝入关以来的第三个皇帝世宗胤，即雍正皇帝的知名度陡然上升。北京胡同里的老太太并她们手里牵着的小孙子，都知道咱大清国有一个雍正皇帝。鄙人生性疏散，向来耐不住电视剧的冗长加唠叨，所以尽管《雍正王朝》几番热播，我却始终没有看过。不过，虽然眼睛没看电视，却依然逃不脱雍正的阴影，因为总是有人来问，雍正到底是个什么东西，是不是像电视剧里说的那样好。说实在的，雍正是个什么东西，我现在也说不好。此公在历史上的名声一直不太好，又偏偏夹在两个名声过大也过好的皇帝之间，想不灰头土脸都难。虽然某作家给他平了一回反，也未必真的能翻过来。此公没有他的老爹康熙那样兴趣广泛，也没有他儿子乾隆那样诗兴泉涌，只有一笔字据说还说得过去（我见过的，无疑比到处题字的乾隆强多了），当政时间不长，又没有多少可说的事情。不过，在我看来，跟其他的清朝皇帝比起来，这个人多少有点怪，让后人面对他的时候总忍不住想说点什么，却往往说不出什么来。正是这个雍正，登基做皇帝，空着正殿乾清宫不住，非要搬到偏殿养心殿忍着，弄得皇宫的政治地理大乱，大家都找不着北。雍正在位的时候，组成了一个机要的秘书班子——军机处，在他以后，军机处取代内阁成为国家的核心决策机关。但是，雍正有秘书却不爱用，总是自己亲自批奏折，往往批得很长，口吻就像个爱唠叨的老太太。不管臣子功劳有多大，让他抓住点小毛病就嗦个没完，非让你灵魂深处爆发革命，将自己批倒批臭而后止。批奏折批得长，不见得天天都那么忙，至少不像周公似的，吃顿饭都要吐出来好几次。所以，雍正也有闲功夫看看戏。看戏可是看戏，别的皇帝看过也就罢了，顶多当时一喜或者一悲，高兴了赏几两银子给扮戏的太监，不高兴了赏他们一顿板子。可是人家雍正不是这样，看戏都能看出一段轶事来。说是一次他看《绣襦记·打子》，此剧是明人根据唐代传奇《李娃传》改编的，说的是名门公子郑元和名妓李亚仙的爱情故事。《打子》一折演的是担任常州刺史的郑父，看到儿子因迷恋娼家最后流落街头，靠为人唱挽歌度日，一怒之下痛打儿子的情节。这段戏让雍正十分高兴，尤其喜欢扮演郑父的小太监（大概更多地是喜欢这种贾政似的人物），于是把他叫到身边赏饭。在吃饭的时候，小太监一时忘情，顺口问了一句，现在的常州刺史是谁？雍正陡然翻转脸皮，勃然大怒，说你这优伶贱辈，怎么敢问国家的名器？当场下令将小太监杖毙廊下。雍正不独性格乖戾，行事还有点天真。从来历史上轮到争位的时候，父子反目、兄弟相残都是免不了的事。胜利者对付政敌，或杀或坑都是应有之意，别人其实也说不出什么更多的来。君不见，李世民杀了两个兄弟，逼他父亲让了位，最后还不是得了明君之名。可是，雍正对付他的两个争位的兄弟，也不杀也不坑，却封他们为“阿其那”（猪）、“塞思黑”（狗）。殊不知，这样的封法细究起来却大有不妥，自家兄弟是猪狗，那他自己呢？他的父亲呢？这还不算雍正行事中最天真的，雍正一生最自以为是的糗事，要算对曾静案的处理。雍正六年（1728年），湖南出了个反清案件，事主名叫曾静，是个屡试不第的儒生，因受到明朝遗臣吕留良诗文的影响，锐意反清。一日，不知从哪里听说现任川陕总督岳钟琪是岳飞的后代，于是让他的弟子张熙前去投书，劝说岳反清。结果不问可知，即使岳钟琪跟曾静一样有华夷情结，也断然不会为了一个岳武穆的遥远虚名而甘冒身家性命之险。于是，这个送上门去的“反革命小集团”被连窝端掉，圣眷正隆的岳钟琪以诱捕曾静洗清了自己。无论在哪个朝代，出几个谋反案件都不稀奇，更何况满清以异族入主中原，虽然过了百十年，乡下的迂儒硬是坚持“民族大义”和华夷之辨，那也是没有办法的事情。不过这次情况大有不同，在查抄出来的“反革命文件”中，居然有大量宣传雍正争夺皇位的内容，说他如何谋父、逼母、弑兄、屠弟，以及贪财、好杀、淫色，等等，几乎跟当年的隋炀帝杨广差不多。这样一来，曾静案就不再是一般反对异族统治的逆案了，而是主要针对雍正个人的谋反行为，这样的逆案无疑更容易引起龙颜大怒。曾静等人被逮到京后，实际上是雍正亲自操纵案件的审理，即使到了今天，我们依然可以从当时的上谕中，窥见雍正的恨恨连声之态。按照传统时代的常理，对于这样一个策动大臣谋反，并对现任皇帝进行恶毒攻击的“反革命小集团”的成员，凌迟处死并夷之九族本是应有之意，只有这样，才可稍解皇帝和拍马屁的臣子们之气于万一。可是，雍正对曾静案的处理，却出乎所有人的意料。雍正下令将审讯曾静的记录整理成册，并在前面加上了长长的按语（上谕），起名为《大义觉迷录》。只是这个审讯记录过于整齐，明显透着点“做”的意思。尽管雍正对曾静等人的“谣言”十分恼怒，认为自己连做梦都想不到，属于犬吠狼嚎，本不足以理会，但在上谕中还是花了很大的篇幅，论证自己对父母如何地好，如何地孝顺，对兄弟如何地仁至义尽，总之是将曾静等人私下散布的所有对他不利的言语，一一详加驳斥。而且“审讯记录”更是采用一问一答的方式，先由审官按雍正的旨意质问，再由曾静作答，让曾静在稍做一点解释之后，将自己骂得狗血淋头，从而反证他散布的有关雍正争位的种种言语是如何地荒诞不经。《大义觉迷录》印行之后，发往各个府州县，每个学宫都备一册，成为学子们的必读书。与此同时，雍正还下令在曾静的家乡湖南成立观风俗使衙门，将曾静、张熙释放，派到观风俗使衙门效力，曾静倒也是个可人，十分配合，不仅自愿到各地宣讲雍正皇帝的“圣德”，而且还写了一篇《归仁说》，表达自己诚心忏悔之意。雍正这么做的意图，事后看来应该是很明白的。他不是不恨曾静这些人，更不是心存仁慈，企图感化顽愚（像某作家说的那样）。因为这个案件涉及到那么多攻击他私德的谣言，他感到委屈，需要有个辩白的机会，否则心中的恶气无论如何也出不来，所以就颇费心思地设计了这样一种处理方式。《大义觉迷录》就是一种特殊形式的辩驳，一种最后将对方彻底而且无条件驳倒的辩驳。让曾静等人自己下去痛骂自己，现身说法，对皇帝的清誉而言，显然比杀了他们要有利得多。然而，自以为聪明而且急于刷洗自己的雍正，却忽视了一个很关键的问题，传统政治是黑幕政治，或者说是黑箱政治，上层的事情，既无必要，也无可能昭示于公众。尽管小道消息可以传得满天飞，但一般不允许有关部门出来解释澄清；时间长了，自然大家对所有的事都糊里糊涂，将信将疑。这种状况，在多数情况下反而有利于政治的操控。雍正为了把自己刷洗干净，将最隐秘的宫廷斗争抖落出来，昭示天下，甚至不知道分个保密等级，结果自然是越抹越黑，许多原来不知道这些谣言的地方，反而都知道了。那修整得过于整齐的“辩驳书”，实际上未必有雍正想像的那样具有说服力，说不定副作用更大。因为雍正没有也不可能改变政治黑幕化的传统，人们还是按照以往的惯例来分析判断事物，正事反看，反事正看，沿着字里行间，寻找微言大义，捕风捉影，发挥想像。事情的结果我们现在都知道了，在清朝诸帝中，关于雍正的传言和非议是最多的。雍正的儿子乾隆是个聪明人，他上台之后，马上下令将曾静、张熙等人处死，收回所有散在地方的《大义觉迷录》，加以销毁，任何人不得收存，否则严加惩处。如此说来，雍正作为皇帝，倒是有几分天真之处，只是这种天真并不可爱。";
			// String detail = "abcdefghijklmno-12345678901234.html";
			Forum f = new ForumMain();
			f.setAgree(0);
			f.setAmend("");
			f.setArtSize(BBSCSUtil.getSysCharsetStrLength(detail));
			f.setAttachFileName(new ArrayList());
			f.setAuditing(0);
			f.setAuditingAttachFile(0);

			f.setBeAgainst(0);
			f.setBoardID(b.getId());
			f.setBoardName(b.getBoardName());
			f.setCanNotDel(0);
			f.setCanNotRe(0);
			f.setClick(0);
			f.setCommend(0);
			f.setDelIP("");
			f.setDelSign(0);
			f.setDelTime(0);
			f.setDelUserID("");
			f.setDelUserName("");
			f.setDetail(detail);
			f.setDoEliteName("");
			f.setDoEliteTime(0);
			f.setEditType(0);
			f.setElite(0);
			f.setEliteID(0);
			f.setFace(0);
			f.setHaveAttachFile(0);
			f.setIpAddress("127.0.0.1");
			f.setIsHidden(0);

			f.setIsLock(0);
			f.setIsNew(1);
			f.setIsTop(0);
			f.setIsVote(0);
			f.setLastPostNickName("---");
			f.setLastPostTitle("");
			f.setLastPostUserName("---");
			f.setLastTime(nowtime);
			f.setMainID("");
			f.setNickName("laoer");
			f.setParentID("");
			f.setPostTime(nowtime);
			f.setPostType(0);
			f.setQuoteText("");
			f.setReNum(0);

			f.setSign("");

			f.setTitle("天真却不可爱的雍正皇帝" + i);
			f.setTitleColor(0);
			f.setUserID("402880e61117a627011117a6c9a70001");
			f.setUserName("laoer");
			f.setEmailInform(0);
			f.setMsgInform(0);
			f.setUserBlog(0);

			f.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX);

			f.setTagID("0");

			f.setTagName("");

			f.setPreviewAttach(0);

			try {
				f = this.getForumService().createForum(f, b, ui, null);

				for (int j = 0; j < 10; j++) {
					Forum rf = new ForumMain();
					rf.setAgree(0);
					rf.setAmend("");
					rf.setArtSize(BBSCSUtil.getSysCharsetStrLength(detail));
					rf.setAttachFileName(new ArrayList());
					rf.setAuditing(0);
					rf.setAuditingAttachFile(0);

					rf.setBeAgainst(0);
					rf.setBoardID(b.getId());
					rf.setBoardName(b.getBoardName());
					rf.setCanNotDel(0);
					rf.setCanNotRe(0);
					rf.setClick(0);
					rf.setCommend(0);
					rf.setDelIP("");
					rf.setDelSign(0);
					rf.setDelTime(0);
					rf.setDelUserID("");
					rf.setDelUserName("");
					rf.setDetail(detail);
					rf.setDoEliteName("");
					rf.setDoEliteTime(0);
					rf.setEditType(0);
					rf.setElite(0);
					rf.setEliteID(0);
					rf.setFace(0);
					rf.setHaveAttachFile(0);
					rf.setIpAddress("127.0.0.1");
					rf.setIsHidden(0);

					rf.setIsLock(0);
					rf.setIsNew(0);
					rf.setIsTop(0);
					rf.setIsVote(0);
					rf.setLastPostNickName("---");
					rf.setLastPostTitle("");
					rf.setLastPostUserName("---");
					rf.setLastTime(nowtime);
					rf.setMainID(f.getMainID());
					rf.setNickName("laoer");
					rf.setParentID("");
					rf.setPostTime(nowtime);
					rf.setPostType(0);
					rf.setQuoteText("");
					rf.setReNum(0);

					rf.setSign("");

					rf.setTitle("天真却不可爱的雍正皇帝");
					rf.setTitleColor(0);
					rf.setUserID("402880e61117a627011117a6c9a70001");
					rf.setUserName("laoer");
					rf.setEmailInform(0);
					rf.setMsgInform(0);
					rf.setUserBlog(0);

					rf.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX);

					rf.setTagID("0");

					rf.setTagName("");

					rf.setPreviewAttach(0);
					rf = this.getForumService().createReForum(rf, f, b, ui, null, false);
				}

			} catch (BbscsException e) {
				e.printStackTrace();
			}
		}
	}

}
