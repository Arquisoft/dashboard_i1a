package persistence.model;

public class Association {

	public static class VoteCommentUser {

		public static void link(User user, VoteComment vote, Comment comment) {
			comment._setUser(user);
			user._getComments().add(comment);
			vote.setUser(user);
			vote.setComment(comment);
		}

		public static void unlink(User user, VoteComment vote, Comment comment) {
			vote.setUser(null);
			vote.setComment(null);
			user._getComments().remove(comment);
			comment._setUser(null);
		}
	}

	public static class VoteProposalUser {

		public static void link(User user, VoteProposal vote, Proposal proposal) {
			proposal.setUser(user);
			user._getProposals().add(proposal);
			vote.setProposal(proposal);
			vote.setUser(user);

		}

		public static void unlink(User user, VoteProposal vote, Proposal proposal) {
			vote.setUser(null);
			vote.setProposal(null);
			user._getProposals().remove(proposal);
			proposal.setUser(null);

		}
	}

	public static class MakeProposal {

		public static void link(User user, Proposal proposal) {
			proposal.setUser(user);
			user._getProposals().add(proposal);
		}

		public static void unlink(User user, Proposal proposal) {
			user._getProposals().remove(proposal);
			proposal.setUser(null);
		}
	}

	public static class CommentProposal {

		public static void link(Comment comment, Proposal proposal) {
			comment._setProposal(proposal);
			proposal._getComments().add(comment);
		}

		public static void unlink(Comment comment, Proposal proposal) {
			proposal._getComments().remove(comment);
			comment._setProposal(null);
		}
	}

}