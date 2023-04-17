import { ListItem, Stack, Typography } from "@mui/material";
import PropTypes from "prop-types";

export default function WordItem({ wordData }) {
  return (
    <ListItem key={wordData.id}>
      <Stack>
        <Typography variant="body2">{wordData.text}</Typography>
        <Typography variant="subtitle2">{wordData.category}</Typography>
      </Stack>
    </ListItem>
  );
}

WordItem.propTypes = {
  wordData: PropTypes.shape({
    id: PropTypes.number,
    text: PropTypes.string,
    category: PropTypes.string,
  }),
};
